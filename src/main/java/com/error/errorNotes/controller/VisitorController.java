package com.error.errorNotes.controller;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesVisitors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public String creerCompte(@RequestBody Utilisateur utilisateur, @PathVariable String email, @PathVariable String password){
       if(servicesVisitors.trouverCompteParEmail(email) == null){
           servicesVisitors.creerCompteUser(utilisateur, email, password);
           return "Votre compte est créée par mot clé";
       }else {
           return "Cet email existe déjà";
       }
    }

    @GetMapping("/readProbleme")
    public List<Probleme> readProbleme(){
        return servicesVisitors.lireProbleme();
    }

    @GetMapping("/readSolution")
    public List<Solution> readSolution(){
        return servicesVisitors.lireSolution();
    }

    @GetMapping("/readCommentaire")
    public List<Commentaire> readCommentaire(){
        return servicesVisitors.lireCommentaire();
    }
}
