package com.error.errorNotes.controller;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesVisitors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* Le controlleur ci-dessous réagit à des demandes venant des simples visitors
* */

@Api(value = "hello", description = "Sample hello world application")
@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorController {

    final private ServicesVisitors servicesVisitors;

    //methode permettant de creer un compte utilisateur
    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/creerCompte/{email}/{password}")
    public String creerCompte(@RequestBody Utilisateur utilisateur, @PathVariable String email, @PathVariable String password){
       if(servicesVisitors.trouverCompteParEmail(email) == null){
           servicesVisitors.creerCompteUser(utilisateur, email, password);
           return "Votre compte est créée avec succes";
       }else {
           return "Cet email existe déjà";
       }
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @GetMapping("/afficherProbleme")
    public List<Probleme> readProbleme(){
        return servicesVisitors.lireProbleme();
    }

    @GetMapping("/afficherSolution")
    public List<Solution> readSolution(){
        return servicesVisitors.lireSolution();
    }

    @GetMapping("/afficherCommentaire")
    public List<Commentaire> readCommentaire(){
        return servicesVisitors.lireCommentaire();
    }
}
