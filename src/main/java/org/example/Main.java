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

        Entrenador entrenador1 =  new Entrenador(1L, "Guardiola", LocalDate.of(1960, 5, 4), 1000, equipo1);
        dao.saveEntrenador(entrenador1);

        dao.asignarEntrenadorAEquipo(1L, 1L);
        System.out.println(equipo1.getEntrenador());
    }
}