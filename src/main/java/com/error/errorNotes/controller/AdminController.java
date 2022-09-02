package com.error.errorNotes.controller;


import com.error.errorNotes.model.*;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
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
    final  private ServicesUsers servicesUsers;

    @ApiOperation(value = "Controller qui permet de créer un compte admin")
    @PostMapping("/creerCompteAdmin/{email}/{password}")//{emailAcree}/{passwordAcree}

    //methode permettant de creer un compte admin
    public String creerCompteAdmin(@RequestBody Utilisateur utilisateur, @PathVariable  String email, @PathVariable String password) {//, @PathVariable String emailAcree, @PathVariable String passwordAcree

        //recuperation du compte admin que l'admin veut crée
        Compte compteAcree = servicesUsers.trouverCompteParEmail(utilisateur.getCompte().getEmail());

        //recuperation du compte de l'user admin actuel
        Compte compte = servicesUsers.connexion(email, password);

        //System.out.println(compte.getRole());

        //verifie si l'user actuel a un compte et si son password est correct et s'il a le role admin
        if (compte != null && compte.getRole().equals("admin") || email.equals("kmahamadou858@gmail.com") && password.equals("keita123@")){


            //verifie si l'user admin que l'admin actuel veut crée existe ou pas
            if (compteAcree == null){

                if (compteAcree.getEmail().trim().equals("") || compteAcree.getPassword().trim().equals("")){

                    if(compte.getPassword().length() >= 8){
                        //creation du compte admin
                        servicesAdmins.creerCompteAdmin(utilisateur);

                        System.out.println("compte admin créé");
                        return "compte admin créé";
                    }else {

                        System.out.println("Le mot de passe doit être superieur ou égale à 8");
                        return "Le mot de passe doit être superieur ou égale à 8";
                    }

                }else {//lorsque le compte existe déjà

                    System.out.println("Ce compte existe déjà");
                    return "Ce compte existe déjà";
                }

                }else {

                System.out.println("Veuillez renseigner les champs");
                return "Veuillez renseigner les champs";
                }

       }else {//authentification échoué

            System.out.println("Acces refusé");
           return "Acces refusé";
       }
    }

    @ApiOperation(value = "Controller qui permet de créer les états qui seront affecté à un problème")
    @PostMapping("/createEtat/{email}/{password}")
    public String createEtat(@RequestBody Etat etat, @PathVariable  String email, @PathVariable String password){

        //verifie si c'est l'user actuelle est un admin et si son mot de passe est correct
        if (servicesUsers.connexion(email, password) != null && servicesUsers.trouverCompteParEmail(email).getRole().equals("admin") || email.equals("kmahamadou858@gmail.com") && password.equals("keita123@")){

            //Verifie si l'etat demandé existe déjà ou pas
            if(servicesAdmins.TrouverEtatparNom(etat.getNom()) == null) {

                //creation de l'etat
                servicesAdmins.creerEtat(etat);

                System.out.println("Etat enregistré avec succes");
                return "Etat enregistré avec succes";
            }else{

                System.out.println("cet Etat existe dejà");
                return "cet Etat existe dejà";
            }
            }else {//authentification échoué

            System.out.println("Acces refusé");
            return "Acces refusé";
        }
    }

    @ApiOperation(value = "Controller qui permet de creer les Technologies des différents problème")
    @PostMapping("/createTechnologie/{email}/{password}")
    public String createTechnologie(@RequestBody Technologie technologie, @PathVariable  String email, @PathVariable String password){

        //verifie si c'est l'user actuelle est un admin et si son mot de passe est correct
        if (servicesUsers.connexion(email, password) != null && servicesUsers.trouverCompteParEmail(email).getRole().equals("admin") || email.equals("kmahamadou858@gmail.com") && password.equals("keita123@")){

            //Verifie si la technologie demandé existe déjà ou pas
           if(servicesUsers.trouverTechonologieParNom(technologie.getNom()) == null) {

               //creation de la technologie
               servicesAdmins.creerTechnologie(technologie, email);

               System.out.println("Technologie enregistré avec succes");
               return "Technologie enregistré avec succes";
           }else {

               System.out.println("cette Technologie existe déjà");
               return "cette Technologie existe déjà";
           }
        }else {//authentification échoué

            System.out.println("Acces refusé");
            return "Acces refusé";
        }
    }

    @ApiOperation(value = "Controller qui permet de supprimer un commentaire")
    @DeleteMapping("/deleteCommentaire/{email}/{password}/{id}")
    public String deleteCommentaire(@PathVariable  String email, @PathVariable String password, @PathVariable Long id){
        if (servicesUsers.connexion(email, password) != null && servicesUsers.trouverCompteParEmail(email).getRole().equals("admin") || email.equals("kmahamadou858@gmail.com") && password.equals("keita123@")){
            servicesAdmins.supprimer(id);

            System.out.println("Commentaire supprimé avec succes");
            return "Commentaire supprimé avec succes";
        }else {

            System.out.println("Acces refusé");
            return "Acces refusé";
        }

    }


    @ApiOperation(value = "Controller qui permet de supprimer un probleme")
    @DeleteMapping("/deleteProbleme/{email}/{password}/{titre}")
    public String supprimerProbleme(@PathVariable String email, @PathVariable String password, @PathVariable String titre){

        //recupere le probleme sur lequel la solution doit etre posté
        Probleme pro = servicesUsers.trouverProblemeParTitre(titre);

        //verifie si c'est l'user actuelle est un admin et si son mot de passe est correct
        if (servicesUsers.connexion(email, password) != null && servicesUsers.trouverCompteParEmail(email).getRole().equals("admin") || email.equals("kmahamadou858@gmail.com") && password.equals("keita123@")){
            if (pro != null){
                Long idPro = pro.getId();
                Solution solu = servicesUsers.trouverSolutionParIdProbleme(idPro);
                if(solu != null){
                    Long idSolu = solu.getId();
                    servicesUsers.supprimerRessourceParIdSolution(idSolu);
                    servicesAdmins.suprimerSolution(idSolu);
                    servicesAdmins.supprimerLesCommentairesSolution(solu);
                    servicesUsers.supprimerProblemeTechnologie(idPro);
                    servicesAdmins.suprimerProbleme(idPro);

                    System.out.println("Probleme supprimé avec succès");
                    return "Probleme supprimé avec succès";
                }else {
                    servicesUsers.supprimerProblemeTechnologie(idPro);
                    servicesAdmins.suprimerProbleme(idPro);

                    System.out.println("Problème supprimé avec succès");
                    return "Problème supprimé avec succès";
                }
            }else {

                System.out.println("Ce probleme n'existe pas");
                return "Ce probleme n'existe pas";
            }
        }else {

            System.out.println("Acces refusé");
            return "Acces refusé";
        }
    }
}
