package com.error.errorNotes.controller;


import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    final private ServicesAdmins servicesAdmins;
    final private ServicesUsers servicesUsers;

    @PostMapping("/createProbleme/{email}/{password}")
    public String createProbleme(@RequestBody Probleme probleme, @PathVariable String email, @PathVariable String password){
        if (servicesUsers.connexion(email, password)){
            servicesUsers.creerProbleme(probleme);
            return "Probleme enregistré avec succes";
        }
        return "Acces refusé";
    }

    @PostMapping("/createSolution/{email}/{password}/{titreProbleme}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password, @PathVariable String titreProbleme){

        //si email et password de l'user sont correct
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

    @PostMapping("/createCommentaire/{email}/{password}")
    public String createCommentaire(@RequestBody Commentaire commentaire, @PathVariable String email, @PathVariable String password){
        if(servicesUsers.connexion(email, password)) {
            servicesUsers.creerCommentaire(commentaire);
            return "Solution enregistré avec succes";
        }
        return "Acces refusé";
    }

}
