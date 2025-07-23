package ec.edu.espe.sincronizacion.service;

import ec.edu.espe.sincronizacion.dto.HoraClienteDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SincronizacionService {
    private final  Map<String,Long> tiempoClientes = new ConcurrentHashMap<>();

    private static final int INTERVALO_SEGUNDOS = 5;

    public void registrarTiempoCliente(HoraClienteDto dto) {
        tiempoClientes.put(dto.getNombreNodo(), dto.getHoraEnviada());
    }

    public void sincronizarRelojes(){
        if(tiempoClientes.size() >=2)
        {
            long ahora= Instant.now().toEpochMilli();
            long promedio = (ahora + tiempoClientes.values().stream().mapToLong(Long::longValue).sum())/(tiempoClientes.size()+1);
            tiempoClientes.clear();
            enviarAjustes(promedio);


        }
    }

    public void enviarAjustes(long promedio) {
        System.out.println("Enviando ajuste a los nodos: " + new Date(promedio) );
    }



}
