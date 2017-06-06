package mx.skylabs.petagram.restAPI.model;

/**
 * Created by sky on 26/05/17.
 */

public class UsuarioResponse {

    private String idDispositivo;
    private String idUsuarioInstagram;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String idDispositivo, String idUsuarioInstagram) {
        this.idDispositivo = idDispositivo;
        this.idUsuarioInstagram = idUsuarioInstagram;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getIdUsuarioInstagram() {
        return idUsuarioInstagram;
    }

    public void setIdUsuarioInstagram(String idUsuarioInstagram) {
        this.idUsuarioInstagram = idUsuarioInstagram;
    }
}
