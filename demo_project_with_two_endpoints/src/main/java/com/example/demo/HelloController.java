package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.message}")
    private String appMessage;

    @GetMapping("/hello")
    public String hello() {
        return "Hola desde Spring Boot en Java!";
    }

    @GetMapping("/bye")
    public String bye() {
        return "Adiós desde Spring Boot en Java! Hasta luego.";
    }

    Profesor newProfesor = new Profesor("Juan", "Matemáticas");

    @GetMapping("/profesor")
    public String profesor() {
        return "Hola, soy el profesor " + newProfesor.getNombre() + " y enseño " + newProfesor.getAsignatura() + "!";
    }

    // Endpoint cloud-native de ejemplo: usa config externa y query params
    @GetMapping("/saludo")
    public String saludo(@RequestParam(name = "nombre", required = false) String nombre) {
        String base = appMessage; // Se puede sobreescribir con APP_MESSAGE env var
        if (nombre != null && !nombre.isBlank()) {
            return base + ", " + nombre + "!";
        }
        return base + "!";
    }
}

