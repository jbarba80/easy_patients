package com.example.juan.actividad_android;

public class Paciente {
    private String nombre;
    private String dni;
    private String tipo;
    public Paciente(String nombre, String dni, String tipo) {
        this.nombre = nombre;
        this.dni = dni;
        this.tipo=tipo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}