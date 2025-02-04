package org.example;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class Jugador {
    @Id
    private Long id;
    private String nombre;
    private String apellidos;

    //Relación unidireccional (non hai referencia a jugadores en equipo)
    // con foreign key en jugador
    @ManyToOne
    @JoinColumn(name = "idEquipo")
    private Equipo equipo;
    private Double altura;
    private Double peso;
    private int numero;
    private int anoDraft;
    private int numeroDraft;
    private int rondaDraft;
    @Enumerated(EnumType.STRING)
    private Posicion posicion;
    private String pais;
    private String colegio;
    private byte[] foto;

    public Jugador() {
    }

    public Jugador(Long id, String nombre, String apellidos, Equipo equipo, Double altura, Double peso, int numero, int anoDraft, int numeroDraft, int rondaDraft, Posicion posicion, String pais, String colegio) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.equipo = equipo;
        this.altura = altura;
        this.peso = peso;
        this.numero = numero;
        this.anoDraft = anoDraft;
        this.numeroDraft = numeroDraft;
        this.rondaDraft = rondaDraft;
        this.posicion = posicion;
        this.pais = pais;
        this.colegio = colegio;
    }

    public Jugador(Long id, String nombre, String apellidos, Equipo equipo, Double altura, Double peso, int numero, int anoDraft, int numeroDraft, int rondaDraft, Posicion posicion, String pais, String colegio, byte[] foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.equipo = equipo;
        this.altura = altura;
        this.peso = peso;
        this.numero = numero;
        this.anoDraft = anoDraft;
        this.numeroDraft = numeroDraft;
        this.rondaDraft = rondaDraft;
        this.posicion = posicion;
        this.pais = pais;
        this.colegio = colegio;
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAnoDraft() {
        return anoDraft;
    }

    public void setAnoDraft(int anoDraft) {
        this.anoDraft = anoDraft;
    }

    public int getNumeroDraft() {
        return numeroDraft;
    }

    public void setNumeroDraft(int numeroDraft) {
        this.numeroDraft = numeroDraft;
    }

    public int getRondaDraft() {
        return rondaDraft;
    }

    public void setRondaDraft(int rondaDraft) {
        this.rondaDraft = rondaDraft;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(id, jugador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", equipo=" + equipo +
                ", altura=" + altura +
                ", peso=" + peso +
                ", numero=" + numero +
                ", anoDraft=" + anoDraft +
                ", numeroDraft=" + numeroDraft +
                ", rondaDraft=" + rondaDraft +
                ", posicion=" + posicion +
                ", pais='" + pais + '\'' +
                ", colegio='" + colegio + '\'' +
                ", foto=" + Arrays.toString(foto) +
                '}';
    }
}
