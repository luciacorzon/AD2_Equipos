package org.example;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class Equipo {
    @Id
    @Column(unique = true)
    private Long idEquipo;
    private String nombre;
    private String ciudad;
    @Enumerated(EnumType.STRING)
    private Conferencia conferencia;
    @Enumerated(EnumType.STRING)
    private Division division;
    private String nombreCompleto;
    @Column(unique = true)
    private String abreviatura;
    // MappedBy fai que non se cree unha columna de clave foránea na táboa Equipo
    // senón que se fai unha relación entre as táboas Equipo e Entrenador,
    // pois xa lle indicamos na clase Entrenador que se cree ahi unha columna
    // para gardar o id do equipo, é dicir, a clave foránea
    // MappedBy implica que non é propietario da relación
    @OneToOne(mappedBy = "equipo")
    private Entrenador entrenador;

    public Equipo() {
    }

    public Equipo(Long idEquipo, String nombre, String ciudad, Conferencia conferencia, Division division, String nombreCompleto, String abreviatura) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.conferencia = conferencia;
        this.division = division;
        this.nombreCompleto = nombreCompleto;
        this.abreviatura = abreviatura;
    }

    public Long getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Long idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Conferencia getConferencia() {
        return conferencia;
    }

    public void setConferencia(Conferencia conferencia) {
        this.conferencia = conferencia;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(idEquipo, equipo.idEquipo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idEquipo);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo=" + idEquipo +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", conferencia=" + conferencia +
                ", division=" + division +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                '}';
    }
}