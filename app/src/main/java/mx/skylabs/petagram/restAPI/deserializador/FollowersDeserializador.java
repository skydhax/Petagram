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

import mx.skylabs.petagram.pojo.Mascota;
import mx.skylabs.petagram.restAPI.model.MascotaResponse;

/**
 * Created by sky on 17/05/17.
 */

public class FollowersDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray("data");
        mascotaResponse.setFollowersId(deserializarFollowersDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<String> deserializarFollowersDeJson(JsonArray followersResponseData) {
        ArrayList<String> followersId = new ArrayList<>();
        for (int i = 0; i < followersResponseData.size() ; i++) {
            JsonObject followerResponseDataObject = followersResponseData.get(i).getAsJsonObject();
            String userId = followerResponseDataObject.get("id").getAsString();
            followersId.add(userId);
        }
        return followersId;
    }
}
