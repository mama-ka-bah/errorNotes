package com.error.errorNotes.controller;


import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import com.error.errorNotes.services.ServicesVisitors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "hello", description = "Sample hello world application")
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    final private ServicesAdmins servicesAdmins;
    final private ServicesVisitors servicesVisitors;
    final  private ServicesUsers servicesUsers;

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/creerCompteAdmin/{email}/{password}/{emailAcree}/{passwordAcree}")
    public String creerCompteAdmin(@RequestBody Utilisateur utilisateur, @PathVariable  String email, @PathVariable String password, @PathVariable String emailAcree, @PathVariable String passwordAcree) {
       if (servicesUsers.connexion(email, password) == true){
           if (servicesVisitors.trouverCompteParEmail(emailAcree) == null){
               servicesAdmins.creerCompteAdmin(utilisateur, emailAcree, passwordAcree);
               return "compte admin créé";
           }
           return "Ce compte existe déjà";
       }else {
           return "Acces refusé";
       }
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createEtat")
    public Etat createEtat(@RequestBody Etat etat){
        return servicesAdmins.creerEtat(etat);
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createTechnologie")
    public Technologie createTechnologie(@RequestBody Technologie technologie){
        return servicesAdmins.creerTechnologie(technologie);
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @DeleteMapping("/DeleteCommentaire/{id}")
    public String deleteCommentaire(@PathVariable Long id){
        return servicesAdmins.supprimer(id);
    }



}
