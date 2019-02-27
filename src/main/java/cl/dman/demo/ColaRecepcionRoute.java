package cl.dman.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ColaRecepcionRoute extends RouteBuilder {

    private final String colaInicio;
    private final String rutaDestino;

    public ColaRecepcionRoute() {
        colaInicio = "activemq:queue:usuarios_prueba";
        rutaDestino = "stream://out";
    }

    public ColaRecepcionRoute(String colaInicio, String rutaDestino) {
        this.colaInicio = colaInicio;
        this.rutaDestino = rutaDestino;
    }

    @Override
    public void configure() {
        from(colaInicio).routeId("route-usuario-recepcion")
            .unmarshal().json(JsonLibrary.Jackson, UsuarioRequest.class)
            .log("Usuario recibido: ${body.nombre}")
            .to(rutaDestino);
    }
}
