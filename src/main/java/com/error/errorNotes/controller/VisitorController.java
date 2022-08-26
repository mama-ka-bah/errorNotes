package com.error.errorNotes.controller;

import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesVisitors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/*
* Le controlleur ci-dessous réagit à des demandes venant des simples visitors
* */


@RestController
@RequestMapping("/visitors")
@AllArgsConstructor
public class VisitorController {

    final private ServicesVisitors servicesVisitors;

    //methode permettant de creer un compte utilisateur
    @PostMapping("/creerCompte/{email}/{password}")
    public Utilisateur creerCompte(@RequestBody Utilisateur utilisateur, @PathVariable String email, @PathVariable String password){
       servicesVisitors.creerCompteUser(utilisateur, email, password);
        return utilisateur;
    }
}
