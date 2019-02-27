package cl.dman.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ColaEnvioRoute extends RouteBuilder {

    private final String rutaInicio;
    private final String direccionCola;

    public ColaEnvioRoute() {
        rutaInicio = "timer://timerEnvio?period=5s";
        direccionCola = "activemq:queue:usuarios_prueba";
    }

    public ColaEnvioRoute(String rutaInicio, String direccionCola) {
        this.rutaInicio = rutaInicio;
        this.direccionCola = direccionCola;
    }

    @Override
    public void configure() {
        from(rutaInicio).routeId("route-usuario-envio")
            .setBody(() -> new UsuarioRequest("email@gmail.com", "Usuario Demo"))
            .marshal().json(JsonLibrary.Jackson)
            .to(direccionCola);
    }
}
