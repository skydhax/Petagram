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

import mx.skylabs.petagram.restAPI.model.LikeResponse;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;

/**
 * Created by sky on 07/06/17.
 */

public class LikeDeserializador implements JsonDeserializer<LikeResponse> {
    @Override
    public LikeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikeResponse likeResponse = gson.fromJson(json,LikeResponse.class);
        JsonObject likeResponseData = json.getAsJsonObject();
        JsonObject resp = likeResponseData.get("meta").getAsJsonObject();
        likeResponse.setCodigo(resp.get("code").getAsInt());
        return likeResponse;
    }
}
