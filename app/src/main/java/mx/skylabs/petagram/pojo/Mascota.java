package mx.skylabs.petagram.pojo;

/**
 * Created by sky on 26/05/16.
 */
public class Mascota {

    private int idMascota;
    private String nombre;
    private int ranking;
    private int foto;


    public Mascota(String nombre, int ranking, int foto){
        this.nombre = nombre;
        this.ranking = ranking;
        this.foto = foto;
    }

    public Mascota(){

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

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

}
