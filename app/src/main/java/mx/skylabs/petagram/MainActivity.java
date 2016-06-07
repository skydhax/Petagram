package mx.skylabs.petagram;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        */

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();


        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mAbout:
                //Toast.makeText(this, "Daniel Reyes Sánchez", Toast.LENGTH_SHORT).show();
                Intent intentAbout = new Intent(this,About.class);
                startActivity(intentAbout);
                break;

            case R.id.mContact:
                Toast.makeText(this, "Contacto", Toast.LENGTH_SHORT).show();

                Intent intentContacto = new Intent(this,Contacto.class);
                startActivity(intentContacto);

                break;

            case R.id.mFavorites:
                Toast.makeText(this, "Favorites", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ListadoMascotas.class);

                /*

                int noMascotas = 0;
                for(int i = 0; i< 5; i++){
                    if(mascotas.get(i).getRanking() == 5){
                        intent.putExtra("Mascota" + noMascotas,mascotas.get(i).getNombre()); //String
                        intent.putExtra("Foto" + noMascotas,mascotas.get(i).getFoto()); //Int
                        noMascotas++;
                    }
                }
                intent.putExtra("noMascotas",noMascotas);



                */
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilMascotaFragment());

        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

    }


    /*

    SwipeRefreshLayout sfiMiIndicadorRefresh;
    ListView lstMiLista;
    RecyclerView.Adapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        agregarFav();

        sfiMiIndicadorRefresh = (SwipeRefreshLayout) findViewById(R.id.sfiMiIndicadorRefresh);
        lstMiLista = (ListView) findViewById(R.id.lstMiLista);

        String[] planetas = getResources().getStringArray(R.array.planetas);
        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas));

        sfiMiIndicadorRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrescandoContenido();
            }
        });
    }


    public void refrescandoContenido(){

        //aqui se puede hacer una consulta a un web service para traer nuevos datos
        //Cargar la lista con más elementos, etc.

        String[] planetas = getResources().getStringArray(R.array.planetas);
        lstMiLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planetas));
        sfiMiIndicadorRefresh.setRefreshing(false);
    }

    public void agregarFav(){
        FloatingActionButton miFab = (FloatingActionButton) findViewById(R.id.miFab); //mi Floating Action Button
        miFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getBaseContext(), getResources().getString(R.string.fab_message), Toast.LENGTH_LONG ).show();
                Snackbar.make(v, getResources().getString(R.string.fab_message),Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.snackbar_message), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.i("SNACKBAR", "CLICK EN SNACKBAR");
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .show();
            }
        });
    }


    */
}
