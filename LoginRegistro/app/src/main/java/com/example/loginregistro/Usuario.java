package com.example.loginregistro;

import org.parceler.Parcel;

@Parcel
public class Usuario {
    String id;
    String username;
    String password;
    String genero;
    Boolean mayorEdad;


    public Usuario(String id, String username, String password, String genero, Boolean mayorEdad) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.genero = genero;
        this.mayorEdad = mayorEdad;
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getMayorEdad() {
        return mayorEdad;
    }

    public void setMayorEdad(Boolean mayorEdad) {
        this.mayorEdad = mayorEdad;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", genero='" + genero + '\'' +
                ", mayorEdad=" + mayorEdad +
                '}';
    }
}
