package org.example.serializadores;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.example.Conferencia;
import org.example.Division;
import org.example.Equipo;

import java.lang.reflect.Type;

public class EquipoSerializer implements JsonSerializer<Equipo> {

    @Override
    public JsonElement serialize(Equipo equipo, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject equipoJson = new JsonObject();

        equipoJson.addProperty("idEquipo", equipo.getIdEquipo());
        equipoJson.addProperty("nombre", equipo.getNombre());
        equipoJson.addProperty("ciudad", equipo.getCiudad());
        equipoJson.addProperty("conferencia", mapearConferencia(equipo.getConferencia()));
        equipoJson.addProperty("division", mapearDivision(equipo.getDivision()));
        equipoJson.addProperty("nombreCompleto", equipo.getNombreCompleto());
        equipoJson.addProperty("abreviatura", equipo.getAbreviatura());

        return equipoJson;
    }

    private String mapearConferencia(Conferencia conferencia) {
        return switch (conferencia) {
            case ESTE -> "EAST";
            case OESTE -> "WEST";
        };
    }

    private String mapearDivision(Division division) {
        return switch (division) {
            case ATLANTICO -> "ATLANTIC";
            case CENTRAL -> "CENTRAL";
            case SURESTE -> "SOUTHEAST";
            case NOROESTE -> "NORTHWEST";
            case PACIFICO -> "PACIFIC";
            case SUROESTE -> "SOUTHWEST";
        };
    }
}

