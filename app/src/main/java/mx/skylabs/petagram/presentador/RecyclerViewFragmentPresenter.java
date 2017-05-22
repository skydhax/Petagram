package mx.skylabs.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.skylabs.petagram.db.ConstructorMascotas;
import mx.skylabs.petagram.fragments.IRecyclerViewFragmentView;
import mx.skylabs.petagram.fragments.RecyclerViewFragment;
import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.adapter.RestAPIAdapter;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by sky on 08/06/16.
 */
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private Context context;
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<String> followersId;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        //obtenerMascotasBaseDatos();
        getRecentMedia();
        //getRecentMediaFromNiebla();
        //getFollowersId();
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
        //iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void getRecentMedia() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        // preparando el modelo para deserializar
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorParaMediaRecent();
        // establecemos la conexión
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        // ejecutamos el request para obtener todas las fotos del usuario (todo el recent media)
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void getRecentMediaFromNiebla() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorParaMediaRecent();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaFromNiebla();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();

                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getFollowersId() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonFollowersId = restAPIAdapter.construyendoGsonDeserializadorParaFollowers();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonFollowersId);
        Call<MascotaResponse> followersResponseCall = endpointsApi.getFollowersId();
        followersResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                followersId = mascotaResponse.getFollowersId();
                for (int i = 0; i < followersId.size(); i++) {
                    String id = followersId.get(i);
                    getRecentMediaFrom(id);
                }
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void getRecentMediaFrom(String userId) {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorParaMediaRecent();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaFrom(userId);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                ArrayList<Mascota> m = new ArrayList<Mascota>();
                m = mascotaResponse.getMascotas();
                mascotas.addAll(m);
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }
        });
    }

}
