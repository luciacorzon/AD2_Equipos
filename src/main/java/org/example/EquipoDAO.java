package org.example;

import com.google.gson.*;

import java.io.*;
import java.util.List;

public class EquipoDAO {
    public static final Gson gson = new GsonBuilder().registerTypeAdapter(Equipo.class, new EquipoDeserializer()).setPrettyPrinting().create();
    public static final String ARCHIVO = "equipos.json";

    public EquipoDAO() {
    }

    public List<Equipo> getAllFromJSON(){
        List<Equipo> equipos = null;

        try (Reader reader = new FileReader(ARCHIVO)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(List.class, new EquipoDeserializer())
                    .create();

            equipos = gson.fromJson(reader, List.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipos;
    }


    public void saveEquipoJSON(Equipo equipo) {
        try (Reader reader = new FileReader(ARCHIVO)) {
            // Leer el JSON existente
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray equiposArray = jsonObject.getAsJsonArray("Equipo");

            // Agregar el nuevo equipo al JSON
            equiposArray.add(gson.toJsonTree(equipo, Equipo.class));

            // Actualizar el objeto JSON
            jsonObject.add("Equipo", equiposArray);

            // Escribir nuevamente al archivo
            try (Writer writer = new FileWriter(ARCHIVO)) {
                gson.toJson(jsonObject, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
