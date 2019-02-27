package cl.dman.demo;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class UsuarioEnvioRouteTest extends CamelTestSupport {
    private final String ORIGEN_CREACION_URI = "direct:usuarioCreacion";
    private final String ORIGEN_ACTUALIZACION_URI = "direct:usuarioActualizacion";

    @Produce(uri = ORIGEN_CREACION_URI)
    private ProducerTemplate producerCreacion;

    @Produce(uri = ORIGEN_ACTUALIZACION_URI)
    private ProducerTemplate producerActualizacion;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new UsuarioEnvioRoute(ORIGEN_CREACION_URI, ORIGEN_ACTUALIZACION_URI);
    }

    @Test
    public void simpleCreacionUsuario() {
        //Arrange
        UsuarioRequest u = new UsuarioRequest("david.mancilla@axity.com", "David Mancilla");

        //Act
        UsuarioRespuesta respuesta = producerCreacion.requestBody(u, UsuarioRespuesta.class);

        //Assert
        Assert.assertEquals("Usuario david.mancilla@axity.com agregado correctamente", respuesta.getMensaje());
    }


    @Test
    public void simpleActualizacionUsuario() {
        //Arrange
        UsuarioRequest u = new UsuarioRequest("david.mancilla@axity.com", "David Mancilla");

        //Act
        UsuarioRespuesta respuesta = producerActualizacion.requestBody(u, UsuarioRespuesta.class);

        //Assert
        Assert.assertEquals("Usuario david.mancilla@axity.com actualizado correctamente", respuesta.getMensaje());
    }
}