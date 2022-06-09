package com.example.portfolioapi.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.portfolioapi.models.PersonaModel;
import com.example.portfolioapi.services.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/login")
public class UserController {

    @Autowired
    private PersonaService personaService;
    @PostMapping
    public ResponseEntity<PersonaModel> login(@Validated @RequestBody PersonaModel user) {

        PersonaModel persona = personaService.getByUsername(user.getUsername());
        if (persona.getPassword().equals(user.getPassword())) {
           // String token = getJWTToken(user.getUsername());
           // persona.setToken(token);
            persona.setPassword(null);
            return ResponseEntity.ok().body(persona);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
