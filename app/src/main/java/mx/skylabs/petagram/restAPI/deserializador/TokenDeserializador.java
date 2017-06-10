package mx.skylabs.petagram.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import mx.skylabs.petagram.restAPI.model.UsuarioResponse;

/**
 * Created by sky on 10/06/17.
 */

public class TokenDeserializador implements JsonDeserializer<UsuarioResponse> {

    @Override
    public UsuarioResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioResponse usuarioResponse = gson.fromJson(json,UsuarioResponse.class);
        JsonObject usuarioObject = json.getAsJsonObject();
        String idDispositivo = usuarioObject.get("idDispositivo").getAsString();
        String idUsuarioInstagram = usuarioObject.get("idUsuarioInstagram").getAsString();
        usuarioResponse.setIdDispositivo(idDispositivo);
        usuarioResponse.setIdUsuarioInstagram(idUsuarioInstagram);
        return usuarioResponse;
    }}
