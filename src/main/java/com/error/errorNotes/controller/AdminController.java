package com.error.errorNotes.controller;

<<<<<<< HEAD
import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.services.ServicesAdmins;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@AllArgsConstructor
public class AdminController {

   @Autowired
    final private ServicesAdmins servicesAdmins;

   @PostMapping("/createEtat")
   public Etat createEtat(@RequestBody Etat etat){
       return servicesAdmins.creerEtat(etat);
   }

    @PostMapping("/createTechnologie")
   public Technologie createTechnologie(@RequestBody Technologie technologie){
       return servicesAdmins.creerTechnologie(technologie);
   }

   @DeleteMapping("/DeleteCommentaire")
   public String deleteCommentaire(@PathVariable Long id){
       return servicesAdmins.supprimer(id);
   }
=======
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

>>>>>>> authentification
}
