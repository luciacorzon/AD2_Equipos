package org.example;

import com.google.gson.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.*;
import java.util.List;

public class EquipoDAO {
    public static final Gson gson = new GsonBuilder().registerTypeAdapter(Equipo.class, new EquipoDeserializer()).setPrettyPrinting().create();
    public static final String ARCHIVO = "equipos.json";
    private final EntityManagerFactory emf = EquiposJPAManager.getEntityManagerFactory(EquiposJPAManager.EQUIPOS_H2);
    private final EntityManager em = emf.createEntityManager();

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
            JsonArray equiposArray;

            // Leer el archivo si existe y tiene contenido válido
            if (new File(ARCHIVO).exists()) {
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                equiposArray = jsonObject.getAsJsonArray("Equipo");
            } else {
                equiposArray = new JsonArray();
            }

            // Verifica si el equipo ya existe en el JSON (puedes comparar por id)
            boolean found = false;
            for (int i = 0; i < equiposArray.size(); i++) {
                JsonObject equipoJson = equiposArray.get(i).getAsJsonObject();
                if (equipoJson.get("idEquipo").getAsLong() == equipo.getIdEquipo()) {
                    equiposArray.set(i, gson.toJsonTree(equipo, Equipo.class));
                    found = true;
                    break;
                }
            }

            if (!found) {
                equiposArray.add(gson.toJsonTree(equipo, Equipo.class));
            }

            JsonObject updatedObject = new JsonObject();
            updatedObject.add("Equipo", equiposArray);

            try (Writer writer = new FileWriter(ARCHIVO)) {
                gson.toJson(updatedObject, writer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void asignarEntrenadorAEquipo(Long idEntrenador, Long idEquipo) {
        em.getTransaction().begin();

        try {
            // Buscar el equipo y el entrenador en la base de datos
            Equipo equipo = em.find(Equipo.class, idEquipo);
            Entrenador entrenador = em.find(Entrenador.class, idEntrenador);

            if (equipo == null || entrenador == null) {
                System.out.println("Equipo o Entrenador no encontrado.");
                return;
            }

            // Establecer la relación bidireccional na entidade
            equipo.setEntrenador(entrenador);
            entrenador.setEquipo(equipo);

            // Guardar los cambios en la base de datos
            em.merge(equipo);
            em.merge(entrenador);

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void saveEquipoBD(Equipo equipo) {
        try {
            em.getTransaction().begin();
            if (equipo.getIdEquipo() == null || em.find(Equipo.class, equipo.getIdEquipo()) == null) {
                em.persist(equipo); // Nuevo equipo
            } else {
                em.merge(equipo); // Actualizar equipo existente
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ó gardar o equipo: " + e.getMessage());
            em.getTransaction().rollback();
        }
    }


    public void saveEntrenador(Entrenador entrenador){
        try {
            em.getTransaction().begin();
            em.persist(entrenador);
            em.getTransaction().commit();
        }catch (Exception e){
            System.err.println("Erro ó gardar o entrenador: " + e.getMessage());
            em.getTransaction().rollback();
        }
    }

    public void getInfoEquipo(Equipo e){
        System.out.println(em.find(Equipo.class, e.getIdEquipo()));

    }
}
