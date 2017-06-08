package mx.skylabs.petagram.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import mx.skylabs.petagram.Contacto;
import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;

/**
 * Created by sky on 13/05/17.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {


    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();

        JsonObject jsonObject = json.getAsJsonObject(); //metaJsonObject
        JsonObject metaJsonObject = jsonObject.getAsJsonObject("meta");
        int meta_code = metaJsonObject.get("code").getAsInt();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray("data");
        mascotaResponse.setMascotas(deserializarContactoDeJson(mascotaResponseData));
        return mascotaResponse;
        /*
        if (meta_code == 200) {
            // 200 es un codigo válido que contiene respuestas válidas

        }*/
    }

    private ArrayList<Mascota> deserializarContactoDeJson(JsonArray mascotaResponseData) {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        for (int i = 0; i < mascotaResponseData.size() ; i++) {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            String media_id = mascotaResponseDataObject.get("id").getAsString();

            JsonObject userJson  = mascotaResponseDataObject.getAsJsonObject("user");
            String id            = userJson.get("id").getAsString();
            String full_name     = userJson.get("full_name").getAsString();

            JsonObject imageJson        = mascotaResponseDataObject.getAsJsonObject("images");
            JsonObject stdResolution    = imageJson.getAsJsonObject("standard_resolution");
            String photo_url            = stdResolution.get("url").getAsString();

            JsonObject likesJson = mascotaResponseDataObject.getAsJsonObject("likes");
            int likes            = likesJson.get("count").getAsInt();


            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(id);
            mascotaActual.setFullName(full_name);
            mascotaActual.setPhotoUrl(photo_url);
            mascotaActual.setRanking(likes);
            mascotaActual.setMediaId(media_id);
            mascotas.add(mascotaActual);
        }
        return mascotas;
    }
}
