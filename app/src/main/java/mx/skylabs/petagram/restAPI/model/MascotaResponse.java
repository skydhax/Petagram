package mx.skylabs.petagram.restAPI.model;

import java.util.ArrayList;

import mx.skylabs.petagram.pojo.Mascota;

/**
 * Created by sky on 13/05/17.
 */

public class MascotaResponse {
    ArrayList<Mascota> mascotas;
    ArrayList<String> followersId;
    Mascota mascota;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public ArrayList<String> getFollowersId() { return followersId; }
    public void  setFollowersId(ArrayList<String> followersId) { this.followersId = followersId; }

    public Mascota getMascota() { return mascota; }
    public void  setMascota(Mascota mascota) { this.mascota = mascota; }
}
