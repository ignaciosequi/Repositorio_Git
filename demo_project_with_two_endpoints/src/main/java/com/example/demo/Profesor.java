package com.example.demo;

public class Profesor extends Persona {
    private String nombre;
    private String asignatura;

    public Profesor(String nombre, String asignatura) {
        super(nombre, 0); // Llamada al constructor de la clase base
        this.asignatura = asignatura;
    }

    public String getNombre() {
        return super.getNombre();
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    public void setEdad(int edad) {
        super.setEdad(edad);
    }
    public void enseñar() {
        System.out.println("El profesor " + getNombre() + " está enseñando " + asignatura);
    }
}
