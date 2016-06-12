package mx.skylabs.petagram.presentador;

import android.content.Context;

import java.util.ArrayList;

import mx.skylabs.petagram.db.ConstructorMascotas;
import mx.skylabs.petagram.fragments.IRecyclerViewFragmentView;
import mx.skylabs.petagram.pojo.Mascota;

/**
 * Created by sky on 08/06/16.
 */
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private Context context;
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.inicializarListaMascotas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }
}
