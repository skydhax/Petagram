package mx.skylabs.petagram.restAPI.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.restAPI.Constants;
import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.deserializador.FollowersDeserializador;
import mx.skylabs.petagram.restAPI.deserializador.LikeDeserializador;
import mx.skylabs.petagram.restAPI.deserializador.MascotaDeserializador;
import mx.skylabs.petagram.restAPI.deserializador.PerfilDeserializador;
import mx.skylabs.petagram.restAPI.deserializador.TokenDeserializador;
import mx.skylabs.petagram.restAPI.model.LikeResponse;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;
import mx.skylabs.petagram.restAPI.model.UsuarioResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sky on 13/05/17.
 */

public class RestAPIAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }


    public Gson construyeGsonDeserializadorParaMediaRecent() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyendoGsonDeserializadorParaFollowers() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new FollowersDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyendoGsonDeserializadorParaLike() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LikeResponse.class, new LikeDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyendoGsonDeserializadorParaPerfil() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new PerfilDeserializador());
        return gsonBuilder.create();
    }


    /* Métodos para la conexión al servidor de Firebase */

    public EndpointsApi establecerConexionRestApiPetagram() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.NOTIFICATIONS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }


    public Gson construyendoGsonDeserializadorParaTokenPetagram() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class,new TokenDeserializador());
        return gsonBuilder.create();
    }


}
