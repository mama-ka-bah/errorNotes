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

    @PostMapping("/createSolution/{email}/{password}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password){
        if(servicesUsers.connexion(email, password)) {
            servicesUsers.creerSolution(solution);
            return "Solution enregistré avec succes";
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
