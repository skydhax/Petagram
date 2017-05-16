package mx.skylabs.petagram.restAPI.model;

import java.util.ArrayList;

import mx.skylabs.petagram.pojo.Mascota;

/**
 * Created by sky on 13/05/17.
 */

public class MascotaResponse {
    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
