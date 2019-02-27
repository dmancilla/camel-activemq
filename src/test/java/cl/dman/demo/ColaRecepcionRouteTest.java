package cl.dman.demo;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;

public class ColaRecepcionRouteTest extends CamelTestSupport {
    private static final String COLA_INICIO = "direct:colaRecepcion";
    private static final String RUTA_DESTINO = "mock:salida";

    @Produce(uri = COLA_INICIO)
    private ProducerTemplate producerCreacion;

    @EndpointInject(uri = RUTA_DESTINO)
    private MockEndpoint mockDestino;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new ColaRecepcionRoute(COLA_INICIO, RUTA_DESTINO);
    }

    @Test
    public void simpleRecepcionUsuario() throws Exception {
        //Arrange
        mockDestino.expectedMessageCount(1);

        //Act
        Object body = "{\"email\":\"email@gmail.com\",\"nombre\":\"Usuario Demo\"}";
        UsuarioRequest respuesta = producerCreacion.requestBody(body, UsuarioRequest.class);

        //Assert
        mockDestino.assertIsSatisfied();
        Assert.assertEquals("email@gmail.com", respuesta.getEmail());
        Assert.assertEquals("Usuario Demo", respuesta.getNombre());

    }


}