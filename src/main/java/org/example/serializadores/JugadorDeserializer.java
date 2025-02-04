package org.example.serializadores;

import com.google.gson.*;
import org.example.Jugador;
import org.example.Posicion;

import java.lang.reflect.Type;
import java.util.Base64;

public class JugadorDeserializer implements JsonDeserializer<Jugador> {
    @Override
    public Jugador deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Long idJugador = jsonObject.get("idJugador").getAsLong();
        String nombre = jsonObject.get("nombre").getAsString();
        String apellidos = jsonObject.get("apellidos").getAsString();
        Double altura = jsonObject.get("altura").getAsDouble();
        Double peso = jsonObject.get("peso").getAsDouble();
        int numero = jsonObject.get("numero").getAsInt();
        int anoDraft = jsonObject.has("anoDraft") ? jsonObject.get("anoDraft").getAsInt() : 0;
        int numeroDraft = jsonObject.has("numeroDraft") ? jsonObject.get("numeroDraft").getAsInt() : 0;
        int rondaDraft = jsonObject.has("rondaDraft") ? jsonObject.get("rondaDraft").getAsInt() : 0;
        Posicion posicion = Posicion.fromCode(jsonObject.get("posicion").getAsString());
        String pais = jsonObject.get("pais").getAsString();
        String colegio = jsonObject.get("colegio").getAsString();
        byte[] foto = jsonObject.has("foto") && !jsonObject.get("foto").isJsonNull() ? Base64.getDecoder().decode(jsonObject.get("foto").getAsString()) : null;

        return new Jugador(idJugador, nombre, apellidos, null, altura, peso, numero, anoDraft, numeroDraft, rondaDraft, posicion, pais, colegio, foto);
    }
}
