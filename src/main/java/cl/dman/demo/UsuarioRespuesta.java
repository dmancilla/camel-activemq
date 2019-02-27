package cl.dman.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioRespuesta {

    private final String mensaje;
    private final String hora;

    public UsuarioRespuesta(String mensaje) {
        this.mensaje = mensaje;
        this.hora = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getHora() {
        return hora;
    }
}
