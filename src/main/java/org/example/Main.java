package org.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EquipoDAO dao = new EquipoDAO();

        List<Equipo> equipos = dao.getAllFromJSON();
        for (Equipo e: equipos){
            System.out.println(e);
            dao.saveEquipoBD(e);
        }

        Equipo equipo1 =  equipos.get(0);
        Equipo equipo2 =  equipos.get(1);

        Entrenador entrenador1 =  new Entrenador(1L, "Guardiola", LocalDate.of(1960, 5, 4), 1000, equipo1);
        dao.saveEntrenador(entrenador1);

        dao.asignarEntrenadorAEquipo(1L, 1L);
        System.out.println(equipo1.getEntrenador());


        /* Exercicio 6.02 */
        //Jugador jugador1 = new Jugador(1L, "Messi", "Nose", equipo1, 1.70, 70.0, 10, 2000, 1, 1, Posicion.BASE, "Argentina", "Colegio", null);

        JugadorDAO jDao = new JugadorDAO();
        List<Jugador> jugadores = jDao.getJugadores();
        Jugador j1 = jugadores.get(0);
        j1.setId(1L);
        jDao.saveJugadorBD(j1);

    }
}