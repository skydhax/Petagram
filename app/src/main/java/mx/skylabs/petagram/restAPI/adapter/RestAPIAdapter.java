package mx.skylabs.petagram.restAPI.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.skylabs.petagram.restAPI.Constants;
import mx.skylabs.petagram.restAPI.EndpointsApi;
import mx.skylabs.petagram.restAPI.deserializador.MascotaDeserializador;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;
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
}