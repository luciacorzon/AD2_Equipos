package org.example;

import com.google.gson.*;
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
            equipo.setConferencia(Conferencia.valueOf(equipoJson.get("conferencia").getAsString()));
            equipo.setDivision(Division.valueOf(equipoJson.get("division").getAsString()));
            equipo.setNombreCompleto(equipoJson.get("nombreCompleto").getAsString());
            equipo.setAbreviatura(equipoJson.get("abreviatura").getAsString());

            equipos.add(equipo);
        }

        return equipos;
    }
}
