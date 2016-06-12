package mx.skylabs.petagram.fragments;

import java.util.ArrayList;

import mx.skylabs.petagram.adapters.MascotaAdaptador;
import mx.skylabs.petagram.pojo.Mascota;

/**
 * Created by sky on 08/06/16.
 */
public interface IRecyclerViewFragmentView {


    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
