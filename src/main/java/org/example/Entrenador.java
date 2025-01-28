package org.example;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idEntrenador;
    private String nombre;
    private LocalDate fechaNacimiento;
    private int salario;
    // JoinColumn fai que se cree unha columna de clave foránea na táboa Entrenador
    // ca clave do equipo que entrena
    // Ademais, como ten a clave foránea, Entrenador é o propietario da relación bidireccional
    // (a relación é bidireccional porque as dúas clases se referencian mutuamente)
    @OneToOne
    @JoinColumn(name = "idEquipo")
    private Equipo equipo;

    public Entrenador() {
    }

    public Entrenador(Long idEntrenador, String nombre, LocalDate fechaNacimiento, int salario, Equipo equipo) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.salario = salario;
        this.equipo = equipo;
    }

    public Long getIdEntrenador() {
        return idEntrenador;
    }

    public void setIdEntrenador(Long idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenador that = (Entrenador) o;
        return Objects.equals(idEntrenador, that.idEntrenador);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idEntrenador);
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "idEntrenador=" + idEntrenador +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", salario=" + salario +
                ", equipo=" + equipo +
                '}';
    }
}
