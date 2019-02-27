package cl.dman.demo;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class ColaEnvioRouteTest extends CamelTestSupport {
    private static final String RUTA_INICIO = "direct:usuarioCreacion";
    private static final String DIRECCION_COLA = "mock:cola";

    @Produce(uri = RUTA_INICIO)
    private ProducerTemplate producerCreacion;

    @EndpointInject(uri = DIRECCION_COLA)
    private MockEndpoint mockDestino;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new ColaEnvioRoute(RUTA_INICIO, DIRECCION_COLA);
    }

    @Test
    public void simpleEnvioUsuario() throws Exception {
        //Arrange
        UsuarioRequest u = new UsuarioRequest("david.mancilla@axity.com", "David Mancilla");
        mockDestino.expectedMessageCount(1);

        //Act
        String body = producerCreacion.requestBody(u, String.class);

        //Assert
        mockDestino.assertIsSatisfied();
        Assert.assertEquals("{\"email\":\"email@gmail.com\",\"nombre\":\"Usuario Demo\"}", body);

    }


}