package com.error.errorNotes.controller;


import com.error.errorNotes.model.*;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import com.error.errorNotes.services.ServicesVisitors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value = "hello", description = "Les cas d'utilisations pour l'administrateur lui seul")
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    final private ServicesAdmins servicesAdmins;
    final private ServicesVisitors servicesVisitors;
    final  private ServicesUsers servicesUsers;

    @ApiOperation(value = "Controller qui permet de créer un compte admin")
    @PostMapping("/creerCompteAdmin/{email}/{password}")//{emailAcree}/{passwordAcree}

    //methode permettant de creer un compte admin
    public String creerCompteAdmin(@RequestBody Utilisateur utilisateur, @PathVariable  String email, @PathVariable String password) {//, @PathVariable String emailAcree, @PathVariable String passwordAcree

        //recuperation du compte admin que l'admin veut crée
        Compte compteAcree = servicesUsers.trouverCompteParEmail(utilisateur.getCompte().getEmail());

        //recuperation du compte de l'user admin actuel
        Compte compte = servicesUsers.trouverCompteParEmail(email);

        System.out.println(compte.getRole());

        //verifie si l'user actuel a un compte et si son password est correct et s'il a le role admin
        if (servicesUsers.connexion(email, password) == true && compte.getRole().equals("admin")){//|| compte.getEmail().equals("kmahamadou10@gmail.com")){

            //verifie si l'user admin que l'admin actuel veut crée existe ou pas
            if (compteAcree == null){

                //creation du compte admin
               servicesAdmins.creerCompteAdmin(utilisateur, utilisateur.getCompte().getEmail(), utilisateur.getCompte().getPassword());

               return "compte admin créé";
           }else {//lorsque le compte existe déjà
               return "Ce compte existe déjà";
           }
       }else {//authentification échoué
           return "Acces refusé";
       }
    }

    @ApiOperation(value = "Controller qui permet de créer les états qui seront affecté à un problème")
    @PostMapping("/createEtat/{email}/{password}")
    public String createEtat(@RequestBody Etat etat, @PathVariable  String email, @PathVariable String password){

        //verifie si c'est l'user actuelle est un admin et si son mot de passe est correct
        if (servicesUsers.connexion(email, password) == true && servicesUsers.trouverCompteParEmail(email).getRole().equals("admin") || email.equals("kmahamadou858@gmail.com") && password.equals("keita123@")){

            //Verifie si l'etat demandé existe déjà ou pas
            if(servicesAdmins.TrouverEtatparNom(etat.getNom()) == null) {

                //creation de l'etat
                servicesAdmins.creerEtat(etat, email);

                return "Etat enregistré avec succes";
            }else{
                return "cet Etat existe dejà";
            }
            }else {//authentification échoué
            return "Acces refusé";
        }
    }

    @ApiOperation(value = "Controller qui permet de creer les Technologies des différents problème")
    @PostMapping("/createTechnologie/{email}/{password}")
    public String createTechnologie(@RequestBody Technologie technologie, @PathVariable  String email, @PathVariable String password){

        //verifie si c'est l'user actuelle est un admin et si son mot de passe est correct
        if (servicesUsers.connexion(email, password) == true && servicesUsers.trouverCompteParEmail(email).getRole().equals("admin") || email.equals("kmahamadou858@gmail.com") && password.equals("keita123@")){

            //Verifie si la technologie demandé existe déjà ou pas
           if(servicesUsers.trouverTechonologieParNom(technologie.getNom()) == null) {

               //creation de la technologie
               servicesAdmins.creerTechnologie(technologie, email);
               return "Technologie enregistré avec succes";
           }else {
               return "cette Technologie existe déjà";
           }
        }else {//authentification échoué
            return "Acces refusé";
        }
    }

    @ApiOperation(value = "Controller qui permet de supprimer un commentaire")
    @DeleteMapping("/deleteCommentaire/{email}/{password}/{id}")
    public String deleteCommentaire(@PathVariable  String email, @PathVariable String password, @PathVariable Long id){
        if (servicesUsers.connexion(email, password) == true && servicesUsers.trouverCompteParEmail(email).getRole().equals("admin")){
            servicesAdmins.supprimer(id);
            return "Commentaire supprimé avec succes";
        }else {
            return "Acces refusé";
        }

    }


}
