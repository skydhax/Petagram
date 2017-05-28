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

public class PerfilDeserializador implements JsonDeserializer<MascotaResponse> {


    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();

        JsonObject jsonObject = json.getAsJsonObject(); //metaJsonObject
        JsonObject metaJsonObject = jsonObject.getAsJsonObject("meta");
        int meta_code = metaJsonObject.get("code").getAsInt();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray("data");
        mascotaResponse.setMascota(deserializarUsuarioDeJson(mascotaResponseData));
        return mascotaResponse;
        /*
        if (meta_code == 200) {
            // 200 es un codigo válido que contiene respuestas válidas

        }*/
    }

    private Mascota deserializarUsuarioDeJson(JsonArray mascotaResponseData) {
        Mascota mascota = new Mascota();

        JsonObject mascotaResponseDataObject = mascotaResponseData.get(0).getAsJsonObject();

        String id            = mascotaResponseDataObject.get("id").getAsString();
        String full_name     = mascotaResponseDataObject.get("full_name").getAsString();
        String photo_url     = mascotaResponseDataObject.get("profile_picture").getAsString();

        mascota.setId(id);
        mascota.setFullName(full_name);
        mascota.setPhotoUrl(photo_url);

        return mascota;
    }
}
