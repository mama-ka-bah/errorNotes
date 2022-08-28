package com.error.errorNotes.controller;


import com.error.errorNotes.model.*;
import com.error.errorNotes.repository.RepositoryProblemeTechnologie;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Api(value = "hello", description = "Sample hello world application")
public class UserController {

    @Autowired
    final private ServicesUsers servicesUsers;
    //final private RepositoryProblemeTechnologie repositoryProblemeTechnologie;

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createProbleme/{email}/{password}/{technos}")
    public String createProbleme(@RequestBody Probleme probleme, @PathVariable String email, @PathVariable String password, @PathVariable String technos) {

        //authentification
        if (servicesUsers.connexion(email, password)) {

            //verifie si le titre mis à l'url a un problème correspondant
            if(servicesUsers.trouverProblemeParTitre(probleme.getTitre()) == null) {

                //recupere le compte par email
                Compte userCompte = servicesUsers.trouverCompteParEmail(email);

                System.out.println(userCompte);

                //recupere l'user correspondant au compte ci-dessus
                Utilisateur user = servicesUsers.trouverUtilisateurParCompte(userCompte);

                //Un tableau qui contenera une technologie par case
                String[] technosTab = technos.split(":");

                System.out.println("Les technologies mises" + technosTab);

                //Initialisation de la liste qui contenera la liste des technologies à enregistrer
                List<Probleme_technologies> listProTechno = new ArrayList<>();

                //ce boolean est utilisé pour verifier si tous les technologies precisées par l'user existe ou pas dans la base
                boolean bool = true;

                //cette boucle sert à parcours les noms des technologies pour recuper
                // les technologies correspondantes afin de les ajouter à la list à de type problemes_technologie
                //qui sera en fin enregistré

                for (String t: technosTab){

                    //Instaciation de la classe Probleme_technologies, utilisé pour stocker aléatoirement
                    //les problemes_technologies recuperer
                    Probleme_technologies proTechno = new Probleme_technologies();

                    //recuperation aléatoire des technologies
                    Technologie techno = servicesUsers.trouverTechonologieParNom(t);

                    //On met la valeur de la variable bool à false lorsqu'une tecnologie sera introuvable dans la base
                    if(techno == null){
                        bool = false;
                    }

                    //On attribue la technologie actuelle à proTechno
                    proTechno.setTechno(techno);

                    //On attribue le proble à proTechno
                    proTechno.setProbleme(probleme);

                    //ajout de problemes_technologie formé à la list à retourner
                    listProTechno.add(proTechno);

                }
                if (bool == true){//on verifie si toutes les technologies ont été retrouvé dans la base

                    //on crée le probleme en lui attribuant l'user actuel
                    servicesUsers.creerProbleme(probleme, user);

                    //repositoryProblemeTechnologie.saveAll(listProTechno);
                    servicesUsers.enregistrerProblemesTechnologies(listProTechno);
                    return "Probleme enregistré avec succes";
                }else {
                    return  "Une des technologie n'existe pas";
                }
            }else {
                return "Ce problème existe déjà veuillez lire la solution";
            }
        }else {
            return "Acces refusé";
        }
    }

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @PostMapping("/createSolution/{email}/{password}/{titreProbleme}/{ressources}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password, @PathVariable String titreProbleme, @PathVariable String ressources) {

        //recupere le probleme sur lequel la solution doit etre  posté
        Probleme prob = servicesUsers.trouverProblemeParTitre(titreProbleme);

        //on verifie si le problème existe ou pas
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

                    //creation de la solution
                    Solution solutionCree = servicesUsers.creerSolution(solution, prob);

                    //list permettant de stocker les resource dans l'objectif de les enregister tous en même temps
                    List<Ressource> ressourceList = new ArrayList<>();

                    //Un tableau qui contenera les ressources par case
                    String[] tabRessources = ressources.split(":");


                    //cette boucle sert à parcours les ressources envoyées pour recuper
                    //et les ajouter un à un, à  la list ressourcesList à l'aide de l'instance de ressource appélé
                    //ress
                    for (String r: tabRessources){

                        //instance de ressource
                        Ressource ress = new Ressource();

                        //attribue le lien doc actuel à ress actuel
                        ress.setLienDoc(r);

                        //on atribue la solution à ress actuelle
                        ress.setSolution(solutionCree);

                        //on stocke ress actuel à ressourceList, la liste à enregistré
                        ressourceList.add(ress);
                    }

                    //enregistrement des different ressources
                    servicesUsers.enregistrerRessource(ressourceList);

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
