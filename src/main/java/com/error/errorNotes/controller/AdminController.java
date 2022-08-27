package com.error.errorNotes.controller;

import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import com.error.errorNotes.services.ServicesVisitors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    final private ServicesAdmins servicesAdmins;
    final private ServicesVisitors servicesVisitors;
    final  private ServicesUsers servicesUsers;

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

}
