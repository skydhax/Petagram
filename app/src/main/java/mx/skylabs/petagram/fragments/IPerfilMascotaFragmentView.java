package mx.skylabs.petagram.fragments;

import java.util.ArrayList;
import mx.skylabs.petagram.adapters.MascotaAdaptador;
import mx.skylabs.petagram.pojo.Mascota;

/**
 * Created by sky on 27/05/17.
 */

public interface IPerfilMascotaFragmentView {

    public void generarGridLayout();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
