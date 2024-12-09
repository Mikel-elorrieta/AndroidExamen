package com.example.listadeusuarios;

import android.util.Log;

import org.parceler.Parcel;

@Parcel
public class Usuario {
    public  int id;
    public String user;
    public String pass;
    public String nombre;
    public String idioma;
    public boolean activo;

    public Usuario() {
    }

    public Usuario(int id, String user, String pass, String nombre, String idioma, boolean activo) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.nombre = nombre;
        this.idioma = idioma;
        this.activo = activo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", nombre='" + nombre + '\'' +
                ", idioma='" + idioma + '\'' +
                ", activo=" + activo +
                '}';
    }
    public void mostrarResultados(){
        Log.d("Usuario", "ID: " + id + ", Usuario: " + user + ", Contraseña: " + pass + ", Nombre: " + nombre + ", Idioma: " + idioma + ", Activo: " + activo);
    }
}
