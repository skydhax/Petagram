package mx.skylabs.petagram.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.adapters.MascotaAdaptador;
import mx.skylabs.petagram.R;
import mx.skylabs.petagram.presentador.IPerfilRecyclerViewFragmentPresenter;
import mx.skylabs.petagram.presentador.PerfilRecyclerViewFragmentPresenter;

import static android.content.Context.MODE_PRIVATE;


public class PerfilMascotaFragment extends Fragment implements IPerfilMascotaFragmentView{

    ArrayList<Mascota> fotosMascota;
    private RecyclerView rvFotosMascota;
    private IPerfilRecyclerViewFragmentPresenter presenter;
    private String userId;
    private TextView tvNombre;
    private ImageView imageView;

    private String nombre;
    private String foto_url;

    public PerfilMascotaFragment(String userId, String nombre, String foto_url) {
        this.userId = userId;
        this.nombre = nombre;
        this.foto_url = foto_url;
    }

    public PerfilMascotaFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment_perfil_mascota,container,false);


        rvFotosMascota = (RecyclerView) view.findViewById(R.id.rvFotosMascota);
        tvNombre = (TextView)view.findViewById(R.id.tvNombre);
        imageView = (ImageView)view.findViewById(R.id.imageView);


        tvNombre.setText(nombre);
        if (foto_url != null) {
            Picasso.with(getActivity())
                    .load(foto_url)
                    .placeholder(R.drawable.tiger1)
                    .into(imageView);
        }

        presenter = new PerfilRecyclerViewFragmentPresenter(this, getContext(),userId);

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.VERTICAL,false);
        //rvFotosMascota.setLayoutManager(gridLayoutManager);

        return view;
    }


    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvFotosMascota.setLayoutManager(gridLayoutManager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvFotosMascota.setAdapter(adaptador);
    }

}
