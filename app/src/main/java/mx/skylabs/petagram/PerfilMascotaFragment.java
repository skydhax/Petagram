package mx.skylabs.petagram;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class PerfilMascotaFragment extends Fragment {

    ArrayList<Mascota> fotosMascota;
    private RecyclerView rvFotosMascota;


    public PerfilMascotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil_mascota,container,false);


        rvFotosMascota = (RecyclerView) view.findViewById(R.id.rvFotosMascota);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        rvFotosMascota.setLayoutManager(gridLayoutManager);

        inicializarListaMascotas();
        inicializarAdaptador();


        return view;
    }


    public void inicializarListaMascotas(){
        fotosMascota = new ArrayList<>();

        fotosMascota.add(new Mascota("Bengalito",0,R.drawable.tiger1));
        fotosMascota.add(new Mascota("Milky",0,R.drawable.cat1));
        fotosMascota.add(new Mascota("Scar",0,R.drawable.tiger2));
        fotosMascota.add(new Mascota("Johnny",0,R.drawable.cat2));
        fotosMascota.add(new Mascota("Grumpy",0,R.drawable.grumpycat));
    }


    public MascotaAdaptador adaptador;
    public void inicializarAdaptador(){
        adaptador = new MascotaAdaptador(fotosMascota,getActivity());
        rvFotosMascota.setAdapter(adaptador);
    }

}
