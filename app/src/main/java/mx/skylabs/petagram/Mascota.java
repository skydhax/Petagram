package mx.skylabs.petagram;

/**
 * Created by sky on 26/05/16.
 */
public class Mascota {

    private String nombre;
    private int ranking;
    private int foto;

    public Mascota(String nombre, int ranking, int foto){
        this.nombre = nombre;
        this.ranking = ranking;
        this.foto = foto;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
}
