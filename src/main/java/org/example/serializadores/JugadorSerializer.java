package org.example.serializadores;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.example.Jugador;

import java.lang.reflect.Type;
import java.util.Base64;

public class JugadorSerializer implements JsonSerializer<Jugador> {
    @Override
    public JsonElement serialize(Jugador jugador, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("idJugador", jugador.getId());
        jsonObject.addProperty("nombre", jugador.getNombre());
        jsonObject.addProperty("apellidos", jugador.getApellidos());
        jsonObject.addProperty("altura", jugador.getAltura());
        jsonObject.addProperty("peso", jugador.getPeso());
        jsonObject.addProperty("numero", jugador.getNumero());
        jsonObject.addProperty("anoDraft", jugador.getAnoDraft());
        jsonObject.addProperty("numeroDraft", jugador.getNumeroDraft());
        jsonObject.addProperty("rondaDraft", jugador.getRondaDraft());
        jsonObject.addProperty("posicion", jugador.getPosicion().toCode());
        jsonObject.addProperty("pais", jugador.getPais());
        jsonObject.addProperty("colegio", jugador.getColegio());
        jsonObject.addProperty("foto", jugador.getFoto() != null ? Base64.getEncoder().encodeToString(jugador.getFoto()) : null);
        return jsonObject;
    }
}

