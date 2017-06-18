package mx.skylabs.petagram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import mx.skylabs.petagram.fragments.PerfilMascotaFragment;
import mx.skylabs.petagram.restAPI.Constants;
import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.adapter.RestAPIAdapter;
import mx.skylabs.petagram.restAPI.model.LikeResponse;
import mx.skylabs.petagram.restAPI.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sky on 17/06/17.
 */

public class DevuelveLike extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String ACTION_LIKE = "LIKE";
        String ACTION_UNFOLLOW = "UNFOLLOW";


        String RECEIVED_ACTION = intent.getAction();

        if (ACTION_LIKE.equals(RECEIVED_ACTION)) {
            enviarNotificacion();
            Toast.makeText(context, "Acabas de devolver el like", Toast.LENGTH_SHORT).show();
        } else { //if (ACTION_UNFOLLOW.equals(RECEIVED_ACTION)) {
            darUnfollow();
            Toast.makeText(context, "Acabas de dar UNFOLLOW", Toast.LENGTH_SHORT).show();
        }



    }

    private void enviarNotificacion() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gson = restAPIAdapter.construyendoGsonDeserializadorParaTokenPetagram();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiPetagram();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.darLikePetagram("10","5","daniel");
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }




    private void verMiPerfil() {

    }


    private void darUnfollow() {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gson = restAPIAdapter.construyendoGsonDeserializadorParaLike();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gson);
        Call<LikeResponse> likeResponseCall = endpointsApi.follow("5472428849", Constants.ACCESS_TOKEN, "unfollow");
        likeResponseCall.enqueue(new Callback<LikeResponse>() {
            @Override
            public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {

            }

            @Override
            public void onFailure(Call<LikeResponse> call, Throwable t) {

            }
        });
    }
}
