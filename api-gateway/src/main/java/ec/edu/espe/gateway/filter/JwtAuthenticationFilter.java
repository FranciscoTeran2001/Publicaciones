package ec.edu.espe.gateway.filter;


import ec.edu.espe.gateway.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthenticationFilter implements GatewayFilter, Ordered {

    @Autowired
    private JwtUtils jwtUtils;

    // Rutas que NO requieren autenticación
    private final List<String> publicRoutes = Arrays.asList(
            "/api/auth/login",
            "/eureka"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();

        // Si es una ruta pública, continuar sin validación
        if (isPublicRoute(path)) {
            return chain.filter(exchange);
        }

        // Verificar si tiene token de autorización
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return handleUnauthorized(exchange);
        }

        String token = authHeader.substring(7);

        if (!jwtUtils.validateJwtToken(token)) {
            return handleUnauthorized(exchange);
        }

        // Agregar información del usuario al header para los microservicios
        String username = jwtUtils.getUserNameFromJwtToken(token);
        List<String> roles = jwtUtils.getRolesFromJwtToken(token);

        ServerWebExchange modifiedExchange = exchange.mutate()
                .request(r -> r.header("X-User-Name", username)
                        .header("X-User-Roles", String.join(",", roles)))
                .build();

        return chain.filter(modifiedExchange);
    }

    private boolean isPublicRoute(String path) {
        return publicRoutes.stream().anyMatch(path::startsWith);
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
