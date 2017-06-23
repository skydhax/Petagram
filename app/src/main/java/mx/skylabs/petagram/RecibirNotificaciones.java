package mx.skylabs.petagram;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.adapter.RestAPIAdapter;
import mx.skylabs.petagram.restAPI.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sky on 22/06/17.
 */

public class RecibirNotificaciones extends AppCompatActivity  {

    private Button btnRecibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recibir_nottificaciones);
        btnRecibir = (Button) findViewById(R.id.btnRecibir);

    }




    public void recibirNotificaciones(View view ) {
        String token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences prefs = getSharedPreferences("USUARIO_ACTUAL", MODE_PRIVATE);
        String id_instagram = prefs.getString("id", "5472428849");

        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiPetagram();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarUsuario(token,id_instagram);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("TOKEN",usuarioResponse.getIdDispositivo());
                Log.d("ID_INSTAGRAM",usuarioResponse.getIdUsuarioInstagram());
                finish();
                onRestart();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });


    }


}
