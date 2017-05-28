package mx.skylabs.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.skylabs.petagram.fragments.IPerfilMascotaFragmentView;
import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.adapter.RestAPIAdapter;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sky on 26/05/17.
 */

public class PerfilRecyclerViewFragmentPresenter implements IPerfilRecyclerViewFragmentPresenter{

    private Context context;
    private IPerfilMascotaFragmentView iPerfilMascotaFragmentView;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public PerfilRecyclerViewFragmentPresenter(IPerfilMascotaFragmentView iPerfilMascotaFragmentView, Context context, String idUser){
        this.iPerfilMascotaFragmentView = iPerfilMascotaFragmentView;
        this.context = context;

        getMediaFromUser(idUser);
    }

    @Override
    public void getMediaFromUser(String idUser) {


        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorParaMediaRecent();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaFrom(idUser);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                ArrayList<Mascota> m = mascotaResponse.getMascotas();
                for (Mascota mas : m) {
                    Log.e("MASCOTA", mas.getFullName());
                    Log.e("MASCOTA", mas.getPhotoUrl());
                    mascotas.add(mas);
                }
                if (mascotas.size() > 0) {
                    mostrarTimelineUsuario();
                }

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Error en la conexión al servidor, intente más tarde", Toast.LENGTH_LONG).show();
            }


        });
    }


    @Override
    public void mostrarTimelineUsuario(){
        /*
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        //iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.generarGridLayout();*/

        iPerfilMascotaFragmentView.inicializarAdaptadorRV(iPerfilMascotaFragmentView.crearAdaptador(mascotas));
        iPerfilMascotaFragmentView.generarGridLayout();

    }


}
