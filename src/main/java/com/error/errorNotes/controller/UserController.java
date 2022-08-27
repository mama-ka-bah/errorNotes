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

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createSolution/{email}/{password}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password){
        if(servicesUsers.connexion(email, password)) {
            servicesUsers.creerSolution(solution);
            return "Solution enregistré avec succes";
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
