package com.error.errorNotes.controller;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesUsers;
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
    final private ServicesUsers servicesUsers;

    //methode permettant de creer un compte utilisateur
    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/creerCompte")//{email}/{password}
    public String creerCompte(@RequestBody Utilisateur utilisateur){
       if(servicesUsers.trouverCompteParEmail(utilisateur.getCompte().getEmail()) == null){
           if(utilisateur.getCompte().getEmail().trim().equals("") || utilisateur.getCompte().getPassword().trim().equals("")){
               return "Veuillez remplir les champs obligatoire";
           }else {
               if (utilisateur.getCompte().getPassword().length() >= 8){
                   servicesVisitors.creerCompteUser(utilisateur, utilisateur.getCompte().getEmail(), utilisateur.getCompte().getPassword());
                   return "Votre compte est créée avec succes";
               }else{
                   return "Le mot de passe doit être superieur à 8 caracteurs";
               }

           }
       }else {
           return "Cet email existe déjà";
       }
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @GetMapping("/afficherProbleme")
    public List<Probleme> readProbleme(){

        return servicesVisitors.lireProbleme();
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @GetMapping("/afficherSolution")
    public List<Solution> readSolution(){

        return servicesVisitors.lireSolution();
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @GetMapping("/afficherCommentaire")
    public List<Commentaire> readCommentaire(){

        return servicesVisitors.lireCommentaire();
    }
}
