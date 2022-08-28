package com.error.errorNotes.controller;


import com.error.errorNotes.model.*;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Api(value = "hello", description = "Sample hello world application")
public class UserController {

    @Autowired
    final private ServicesUsers servicesUsers;

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createProbleme/{email}/{password}")
    public String createProbleme(@RequestBody Probleme probleme, @PathVariable String email, @PathVariable String password) {

        if (servicesUsers.connexion(email, password)) {
            if(servicesUsers.trouverProblemeParTitre(probleme.getTitre()) == null) {
                //recupere l'id du compte par email
                Compte userCompte = servicesUsers.trouverCompteParEmail(email);

                System.out.println(userCompte);

                Utilisateur user = servicesUsers.trouverUtilisateurParCompte(userCompte);
                //if ()
                servicesUsers.creerProbleme(probleme, user);
                return "Probleme enregistré avec succes";
            }else {
                return "Ce problème existe déjà veuillez lire la solution";
            }
        }else {
            return "Acces refusé";
        }
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createSolution/{email}/{password}/{titreProbleme}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password, @PathVariable String titreProbleme) {

        //recupere le probleme sur lequel la solution doit etre  posté
        Probleme prob = servicesUsers.trouverProblemeParTitre(titreProbleme);

        if(prob != null) {
            //recuperation de l'id du problème
            Long idPro = prob.getId();

            //recupere l'id de l'utilisateur qui a posté le problème
            Long id_userProbleme = servicesUsers.trouverProblemeParId(idPro).getUtilisateur().getId();

            //Recuperation du compte de l'utilisateur qui veut resoudre le problème par son email
            Compte compte_user = servicesUsers.trouverCompteParEmail(email);

            //recuperation de l'id de l'user qui veut poster une solution
            Long id_userSolution = servicesUsers.trouverUtilisateurParCompte(compte_user).getId();

            System.out.println(id_userSolution);
            System.out.println(id_userProbleme);


            //si email et password de l'user sont correct
            if (servicesUsers.connexion(email, password) && id_userProbleme.equals(id_userSolution)) {

                //verfie si le probleme specifié a une solution ou pas
                if (servicesUsers.trouverSolutionParIdProbleme(idPro) == null) {

                    //creation du problème
                    servicesUsers.creerSolution(solution, prob);

                    return "Solution enregistré avec succes";

                } else {//si le problème a déjà une solution

                    return "Ce probleme a été déjà resolu";
                }
            } else {//authentification echoué ou problème n' pas été posté par l'utilisateur
                return "Acces refusé";
            }
        }else {//Si le problème n'existe pas
            return "Ce problème n'existe pas";
        }

        }

        @ApiOperation(value = "Just to test the sample test api of My App Service")
        @PostMapping("/createCommentaire/{email}/{password}/{titreProbleme}")
        public String createCommentaire (@RequestBody Commentaire commentaire, @PathVariable String
        email, @PathVariable String password, @PathVariable String titreProbleme){

            //Authentification
            if (servicesUsers.connexion(email, password)) {

                //recupere le probleme correspondant au titre mis à l'url
                Probleme probleme = servicesUsers.trouverProblemeParTitre(titreProbleme);

                //verifie si le probleme existe ou pas
                if (probleme != null){

                    //recupere l'id du probleme
                    Long idProbleme = probleme.getId();

                    //recupere la solution correspondant au probleme
                    Solution solution = servicesUsers.trouverSolutionParIdProbleme(idProbleme);

                    //recuperation de l'user par son email
                    Compte compteUser = servicesUsers.trouverCompteParEmail(email);

                    //recuperation de l'utilisateur par son compte
                    Utilisateur user = servicesUsers.trouverUtilisateurParCompte(compteUser);

                    //creation du commentaire
                    servicesUsers.creerCommentaire(commentaire, user, solution);
                    return "Commentaire enregistré avec succes";
                }else {//si on trouve pas le probleme
                    return "Ce probleme n'existe pas";
                }
            }else{//au cas ou l'authentification echouera
                return "Acces refusé";
            }

    }
}
