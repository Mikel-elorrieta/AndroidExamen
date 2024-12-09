package com.example.cartasmagic;

import android.util.Log;

import org.parceler.Parcel;

@Parcel
public class Carta {
    String id;
    String nombre;
    String color;
    String tipo;
    String rareza;
    String descripcion;
    Boolean favorito;

    public Carta() {
    }

    public Carta(String id, String nombre, String color, String tipo, String rareza, String descripcion, Boolean favorito) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.tipo = tipo;
        this.rareza = rareza;
        this.descripcion = descripcion;
        this.favorito = favorito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRareza() {
        return rareza;
    }

    public void setRareza(String rareza) {
        this.rareza = rareza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public String toString() {
        return "Carta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", tipo='" + tipo + '\'' +
                ", rareza='" + rareza + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", favorito=" + favorito +
                '}';
    }

    public void mostrarCarta() {
        System.out.println("Carta{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", tipo='" + tipo + '\'' +
                ", rareza='" + rareza + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", favorito=" + favorito +
                '}');
    }
}