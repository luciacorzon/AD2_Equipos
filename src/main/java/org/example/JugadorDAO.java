package org.example;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.serializadores.EquipoDeserializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JugadorDAO {
    public static final String ARCHIVO_JUGADORES = "jugadores.json";
    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Equipo.class, new EquipoDeserializer())
            .setPrettyPrinting()
            .create();
    private final EntityManagerFactory emf = EquiposJPAManager.getEntityManagerFactory(EquiposJPAManager.EQUIPOS_H2);
    private final EntityManager em = emf.createEntityManager();

    public JugadorDAO() {}

    // Lee los jugadores desde un archivo JSON
    // Lee los jugadores desde un archivo JSON
    public List<Jugador> getJugadores() {
        try (FileReader reader = new FileReader(ARCHIVO_JUGADORES)) {
            // Leemos el JSON como un objeto que contiene la lista bajo la clave "Jugador"
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonElement jugadoresArray = jsonObject.get("Jugador");
            Type listType = new TypeToken<List<Jugador>>() {}.getType();
            return gson.fromJson(jugadoresArray, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Guarda un jugador en la base de datos
    public void saveJugadorBD(Jugador jugador) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(jugador);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    // Elimina un jugador de la base de datos
    public void deleteJugadorBD(Jugador jugador) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Jugador managedJugador = em.find(Jugador.class, jugador.getId());
            if (managedJugador != null) {
                em.remove(managedJugador);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    // Guarda una lista de jugadores en un archivo JSON
    public void saveJugadoresToJson(List<Jugador> jugadores) {
        try (FileWriter writer = new FileWriter(ARCHIVO_JUGADORES)) {
            gson.toJson(jugadores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Consulta todos los jugadores de la base de datos
    public List<Jugador> getJugadoresBD() {
        TypedQuery<Jugador> query = em.createQuery("SELECT j FROM Jugador j", Jugador.class);
        return query.getResultList();
    }
}
