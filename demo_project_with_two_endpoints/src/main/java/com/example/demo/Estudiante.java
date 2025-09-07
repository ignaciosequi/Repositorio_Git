package com.example.demo;

public class Estudiante extends Persona {
    private String nombre;
    private String curso;

    public Estudiante(String nombre, String curso) {
        super(nombre, 0); // Llamada al constructor de la clase base
        this.curso = curso;
    }

    public String getNombre() {
        return super.getNombre();
    }

    public String getCurso() {
        return curso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setEdad(int edad) {
        super.setEdad(edad);
    }
@Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + getNombre() + '\'' +
                ", curso='" + curso + '\'' +
                ", edad=" + getEdad() +
                '}';
    }
    interface EstudianteInterface {
        String getNombre();
        String getCurso();
        int getEdad();
    }
}

