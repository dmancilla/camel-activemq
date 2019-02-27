package cl.dman.demo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(description = "Representa un usuario del sistema")
public class UsuarioRequest {

    @ApiModelProperty(value = "El email del usuario", required = true)
    private String email;

    @ApiModelProperty(value = "El nombre del usuario", required = true)
    private String nombre;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
