package com.error.errorNotes.controller;


import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "hello", description = "Sample hello world application")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    final private ServicesAdmins servicesAdmins;
    final private ServicesUsers servicesUsers;

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createProbleme/{email}/{password}")
    public String createProbleme(@RequestBody Probleme probleme, @PathVariable String email, @PathVariable String password){
        if (servicesUsers.connexion(email, password)){
            servicesUsers.creerProbleme(probleme);
            return "Probleme enregistré avec succes";
        }
        return "Acces refusé";
    }

<<<<<<< HEAD
    @PostMapping("/createSolution/{email}/{password}/{titreProbleme}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password, @PathVariable String titreProbleme){

        //si email et password de l'user sont correct
=======
    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createSolution/{email}/{password}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password){
>>>>>>> 229da2f1592c3baea0ee6c684cccbe03b3292bee
        if(servicesUsers.connexion(email, password)) {

           //recupere le probleme sur lequel la solution doit etre  posté
            Probleme prob = servicesUsers.trouverProblemeParTitre(titreProbleme);

            //verifie si le problème specifier existe et qu'il n'a pas de solution
            if (prob != null){
                if (servicesUsers.trouverSolutionParIdProbleme(prob.getId()) == null){
                    //creation du problème
                    servicesUsers.creerSolution(solution, prob);
                    return "Solution enregistré avec succes";
                }else {
                    return "Ce probleme a été déjà resolu";
                }
            }else {
                return "Ce problème n'existe pas";
            }
        }
        return "Acces refusé";
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createCommentaire/{email}/{password}")
    public String createCommentaire(@RequestBody Commentaire commentaire, @PathVariable String email, @PathVariable String password){
        if(servicesUsers.connexion(email, password)) {
            servicesUsers.creerCommentaire(commentaire);
            return "Solution enregistré avec succes";
        }
        return "Acces refusé";
    }

}
