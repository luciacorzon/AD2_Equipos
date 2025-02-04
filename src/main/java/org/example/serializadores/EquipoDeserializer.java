package org.example.serializadores;

import com.google.gson.*;
import org.example.Conferencia;
import org.example.Division;
import org.example.Equipo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EquipoDeserializer implements JsonDeserializer<List<Equipo>> {

    @Override
    public List<Equipo> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Equipo> equipos = new ArrayList<>();

        JsonObject jsonObject = json.getAsJsonObject();
        JsonArray equiposJson = jsonObject.getAsJsonArray("Equipo");

        for (JsonElement element : equiposJson) {
            JsonObject equipoJson = element.getAsJsonObject();

            Equipo equipo = new Equipo();
            equipo.setIdEquipo(equipoJson.get("idEquipo").getAsLong());
            equipo.setNombre(equipoJson.get("nombre").getAsString());
            equipo.setCiudad(equipoJson.get("ciudad").getAsString());
            equipo.setConferencia(mapearConferencia(equipoJson.get("conferencia").getAsString()));
            equipo.setDivision(mapearDivision(equipoJson.get("division").getAsString()));
            equipo.setNombreCompleto(equipoJson.get("nombreCompleto").getAsString());
            equipo.setAbreviatura(equipoJson.get("abreviatura").getAsString());

            equipos.add(equipo);
        }

        return equipos;
    }

    private Conferencia mapearConferencia(String conferencia) {
        return switch (conferencia) {
            case "EAST" -> Conferencia.ESTE;
            case "WEST" -> Conferencia.OESTE;
            default -> throw new JsonParseException("Conferencia desconocida: " + conferencia);
        };
    }

    private Division mapearDivision(String division) {
        return switch (division) {
            case "ATLANTIC" -> Division.ATLANTICO;
            case "CENTRAL" -> Division.CENTRAL;
            case "SOUTHEAST" -> Division.SURESTE;
            case "NORTHWEST" -> Division.NOROESTE;
            case "PACIFIC" -> Division.PACIFICO;
            case "SOUTHWEST" -> Division.SUROESTE;
            default -> throw new JsonParseException("División desconocida: " + division);
        };
    }
}
