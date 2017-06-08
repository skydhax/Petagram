package mx.skylabs.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.restAPI.Constants;
import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.adapter.RestAPIAdapter;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import mx.skylabs.petagram.restAPI.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sky on 27/05/17.
 */

public class ConfigurarCuenta extends AppCompatActivity {

    private EditText etUsuario;
    private Button btnConfigurar;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configurar_cuenta);

        etUsuario = (EditText)findViewById(R.id.etUsuario);
        btnConfigurar = (Button)findViewById(R.id.btnConfigurar);
        context = this;
    }


    public void configurarCuenta(View view){
        buscarUsuario(etUsuario.getText().toString());
    }

    public void buscarUsuario(String nombre) {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonProfile = restAPIAdapter.construyendoGsonDeserializadorParaPerfil();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiInstagram(gsonProfile);
        Log.d("NOMBRE",nombre);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getUser(nombre, Constants.ACCESS_TOKEN);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                Mascota m = mascotaResponse.getMascota();
                m.getId();

                SharedPreferences.Editor editor = getSharedPreferences("USUARIO_ACTUAL", MODE_PRIVATE).edit();
                editor.putString("id", m.getId());
                editor.putString("nombre", m.getFullName());
                editor.putString("photo_url", m.getPhotoUrl());
                editor.apply();


                String token = FirebaseInstanceId.getInstance().getToken();

                registrarUsuario(token,m.getId());
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

            }
        });
    }


    public void registrarUsuario(String idDipositivo, String idUsuarioInstagram) {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        EndpointsApi endpointsApi = restAPIAdapter.establecerConexionRestApiPetagram();
        Call<UsuarioResponse> usuarioResponseCall = endpointsApi.registrarUsuario(idDipositivo,idUsuarioInstagram);
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
