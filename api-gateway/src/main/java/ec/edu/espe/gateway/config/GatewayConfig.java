package ec.edu.espe.gateway.config;


import ec.edu.espe.gateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Rutas para autenticación (SIN filtro JWT)
                .route("auth-login", r -> r.path("/api/auth/**")
                        .uri("lb://SERVICE-AUTH"))

                .route("auth-roles", r -> r.path("/api/roles/**")
                        .uri("lb://SERVICE-AUTH"))

                // Rutas protegidas para publicaciones (CON filtro JWT)
                .route("publicaciones-autores", r -> r.path("/api/v1/autores/**")
                        .filters(f -> f.stripPrefix(2).filter(jwtAuthenticationFilter))
                        .uri("lb://SERVICE-PUBLICACIONES"))

                .route("publicaciones-libros", r -> r.path("/api/v1/libros/**")
                        .filters(f -> f.stripPrefix(2).filter(jwtAuthenticationFilter))
                        .uri("lb://SERVICE-PUBLICACIONES"))

                .route("publicaciones-papers", r -> r.path("/api/v1/papers/**")
                        .filters(f -> f.stripPrefix(2).filter(jwtAuthenticationFilter))
                        .uri("lb://SERVICE-PUBLICACIONES"))

                // Rutas protegidas para notificaciones (CON filtro JWT)
                .route("notificaciones", r -> r.path("/api/v1/notificaciones/**")
                        .filters(f -> f.stripPrefix(2).filter(jwtAuthenticationFilter))
                        .uri("lb://SERVICE-NOTIFICACIONES"))

                // Rutas protegidas para catálogo (CON filtro JWT)
                .route("catalogo", r -> r.path("/api/v1/catalogo/**")
                        .filters(f -> f.stripPrefix(2).filter(jwtAuthenticationFilter))
                        .uri("lb://SERVICE-CATALOGO"))

                .build();
    }
}
