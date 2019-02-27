package cl.dman.demo;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEnvioRoute extends RouteBuilder {

    private final String rutaCreacion;
    private final String rutaActualizacion;

    public UsuarioEnvioRoute() {
        this.rutaCreacion = "direct:usuarioCreacion";
        this.rutaActualizacion = "direct:usuarioActualizacion";
    }

    public UsuarioEnvioRoute(String rutaCreacion, String rutaActualizacion) {
        this.rutaCreacion = rutaCreacion;
        this.rutaActualizacion = rutaActualizacion;
    }

    @Override
    public void configure() {
        inicializarRest();

        //Ruta de entrada de creacion de usuario
        rest("/usuarios").id("rest-usuarioCrear").description("Servicio REST de creacion de usuarios")
            .post("/").description("Crea un usuario").type(UsuarioRequest.class)
            .param().name("body").type(RestParamType.body).description("El usuario a crear").endParam()
            .responseMessage().code(HttpStatus.OK.value()).message("Usuario creado").endResponseMessage()
            .to(rutaCreacion);

        //Ruta de entrada de actualizacion de usuario
        rest("/usuarios").description("Servicio REST de actualizacion de usuarios")
            .put("/").description("Actualizar un usuario").type(UsuarioRequest.class)
            .param().name("body").type(RestParamType.body).description("El usuario a actualizar").endParam()
            .responseMessage().code(HttpStatus.OK.value()).message("Usuario actualizado").endResponseMessage()
            .to(rutaActualizacion);

        //Ruta de creacion de usuario
        from(rutaCreacion).id("route-usuarioCreacion")
            .log("Se esta creando el usuario ${body}")
            .process(exchange -> {
                UsuarioRequest body = exchange.getIn().getBody(UsuarioRequest.class);
                //TODO: Procesar el usuario
                UsuarioRespuesta respuesta = new UsuarioRespuesta("Usuario " + body.getEmail() + " agregado correctamente");
                exchange.getIn().setBody(respuesta);
            })
            .log("Se creó el usuario ${body}")
            .process(exchange -> System.out.println(exchange.getIn().getBody(UsuarioRequest.class)))

        ;

        //Ruta de actualizacion de usuario
        from(rutaActualizacion).id("route-usuarioActualizacion")
            .process(exchange -> {
                UsuarioRequest body = exchange.getIn().getBody(UsuarioRequest.class);
                //TODO: Actualizar el usuario
                UsuarioRespuesta respuesta = new UsuarioRespuesta("Usuario " + body.getEmail() + " actualizado correctamente");
                exchange.getIn().setBody(respuesta);
            })
        ;
    }

    private void inicializarRest() {
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.json)
            .dataFormatProperty("prettyPrint", "true")
            .enableCORS(true)
            .contextPath("/api")
            .apiContextPath("/api-doc")
            .apiProperty("api.title", "API de Usuario")
            .apiProperty("api.version", "1.0");

    }
}
