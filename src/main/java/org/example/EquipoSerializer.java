package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class EquipoSerializer implements JsonSerializer<Equipo> {

    @Override
    public JsonElement serialize(Equipo equipo, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject equipoJson = new JsonObject();

        equipoJson.addProperty("idEquipo", equipo.getIdEquipo());
        equipoJson.addProperty("nombre", equipo.getNombre());
        equipoJson.addProperty("ciudad", equipo.getCiudad());
        equipoJson.addProperty("conferencia", equipo.getConferencia().toString());
        equipoJson.addProperty("division", equipo.getDivision().toString());
        equipoJson.addProperty("nombreCompleto", equipo.getNombreCompleto());
        equipoJson.addProperty("abreviatura", equipo.getAbreviatura());

        return equipoJson;
    }
}
