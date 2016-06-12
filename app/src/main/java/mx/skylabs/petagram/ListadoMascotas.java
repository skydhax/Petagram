package mx.skylabs.petagram;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import mx.skylabs.petagram.adapters.MascotaAdaptador;
import mx.skylabs.petagram.db.ConstructorMascotas;
import mx.skylabs.petagram.pojo.Mascota;

public class ListadoMascotas extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private Bundle parametros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_mascotas);
        /*
        parametros = getIntent().getExtras();
        */
        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listaMascotas = (RecyclerView)findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);

        inicializarListaMascotas();
        inicializarAdaptador();
    }


    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        ConstructorMascotas constructorMascotas = new ConstructorMascotas(this); // la activity representa nuestro contexto
        mascotas = constructorMascotas.obtenerUltimasMascotas();
    }


    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas,this);
        listaMascotas.setAdapter(adaptador);
    }
}
