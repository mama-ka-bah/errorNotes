package com.error.errorNotes.controller;


import com.error.errorNotes.model.*;
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
@Api(value = "hello", description = "Les cas d'utilisations pour l'utilisateur")
public class UserController {

    @Autowired
    final private ServicesUsers servicesUsers;
    //final private RepositoryProblemeTechnologie repositoryProblemeTechnologie;

    //creation du probleme
    @ApiOperation(value = "Controller qui permet de créer un problème")
    @PostMapping("/createProbleme/{email}/{password}/{technos}")
    public String createProbleme(@RequestBody Probleme probleme, @PathVariable String email, @PathVariable String password, @PathVariable String technos) {

        //authentification
        if (servicesUsers.connexion(email, password) != null) {

            //verifie si le titre mis à l'url a un problème correspondant
            if (servicesUsers.trouverProblemeParTitre(probleme.getTitre()) == null) {

                //recupere le compte par email
                Compte userCompte = servicesUsers.trouverCompteParEmail(email);


                //recupere l'user correspondant au compte ci-dessus
                Utilisateur user = servicesUsers.trouverUtilisateurParCompte(userCompte);

                //Un tableau qui contenera une technologie par case
                String[] technosTab = technos.split(" ");

                //Initialisation de la liste qui contenera la liste des technologies à enregistrer
                List<Probleme_technologies> listProTechno = new ArrayList<>();

                //ce boolean est utilisé pour verifier si tous les technologies precisées par l'user existe ou pas dans la base
                boolean bool = true;

                //cette boucle sert à parcours les noms des technologies pour recuper
                // les technologies correspondantes afin de les ajouter à la list à de type problemes_technologie
                //qui sera en fin enregistré

                for (String t : technosTab) {

                    //Instaciation de la classe Probleme_technologies, utilisé pour stocker aléatoirement
                    //les problemes_technologies recuperer
                    Probleme_technologies proTechno = new Probleme_technologies();

                    //recuperation aléatoire des technologies
                    Technologie techno = servicesUsers.trouverTechonologieParNom(t);

                    //On met la valeur de la variable bool à false lorsqu'une tecnologie sera introuvable dans la base
                    if (techno == null) {
                        bool = false;
                    }

                    //On attribue la technologie actuelle à proTechno
                    proTechno.setTechno(techno);

                    //On attribue le proble à proTechno
                    proTechno.setProblemet(probleme);

                    //ajout de problemes_technologie formé à la list à retourner
                    listProTechno.add(proTechno);

                }
                if (bool == true) {//on verifie si toutes les technologies ont été retrouvé dans la base

                    //on crée le probleme en lui attribuant l'user actuel
                    servicesUsers.creerProbleme(probleme, user);

                    //repositoryProblemeTechnologie.saveAll(listProTechno);
                    servicesUsers.enregistrerProblemesTechnologies(listProTechno);

                    System.out.println("Probleme enregistré avec succes");
                    return "Probleme enregistré avec succes";
                } else {
                    System.out.println("Une des technologie n'existe pas");
                    return "Une des technologie n'existe pas";
                }
            } else {
                System.out.println("Ce problème existe déjà veuillez lire la solution");
                return "Ce problème existe déjà veuillez lire la solution";
            }
        } else {
            System.out.println("Acces refusé");
            return "Acces refusé";
        }
    }

    //creation de la solution
    @ApiOperation(value = "Controller qui permet de donner une solution a un problème")
    @PostMapping("/createSolution/{email}/{password}/{titreProbleme}/{ressources}")
    public String createSolution(@RequestBody Solution solution, @PathVariable String email, @PathVariable String password, @PathVariable String titreProbleme, @PathVariable String ressources) {

        //recupere le probleme sur lequel la solution doit etre  posté
        Probleme prob = servicesUsers.trouverProblemeParTitre(titreProbleme);

        //on verifie si le problème existe ou pas
        if (prob != null) {
            //recuperation de l'id du problème
            Long idPro = prob.getId();

            //recupere l'id de l'utilisateur qui a posté le problème
            Long id_userProbleme = servicesUsers.trouverProblemeParId(idPro).getUtilisateur().getId();

            //Recuperation du compte de l'utilisateur qui veut resoudre le problème par son email
            Compte compte_user = servicesUsers.trouverCompteParEmail(email);

            //recuperation de l'id de l'user qui veut poster une solution
            Long id_userSolution = servicesUsers.trouverUtilisateurParCompte(compte_user).getId();


            //si email et password de l'user sont correct
            if (servicesUsers.connexion(email, password) != null && id_userProbleme.equals(id_userSolution)) {
                if(prob.getEtat().getNom().equals("Fermé")){

                    System.out.println("Ce probleme a été fermé");
                    return "Ce probleme a été fermé";
                }else{

                    //verfie si le probleme specifié a une solution ou pas
                    if (servicesUsers.trouverSolutionParIdProbleme(idPro) == null) {

                        //creation de la solution
                        Solution solutionCree = servicesUsers.creerSolution(solution, prob);

                        //list permettant de stocker les resource dans l'objectif de les enregister tous en même temps
                        List<Ressource> ressourceList = new ArrayList<>();

                        //Un tableau qui contenera les ressources par case
                        String[] tabRessources = ressources.split(" ");


                        //cette boucle sert à parcours les ressources envoyées pour recuper
                        //et les ajouter un à un, à  la list ressourcesList à l'aide de l'instance de ressource appélé
                        //ress
                        for (String r : tabRessources) {

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

                        System.out.println("Solution enregistré avec succes");
                        return "Solution enregistré avec succes";

                    } else {//si le problème a déjà une solution

                        System.out.println("Ce probleme a été déjà resolu");
                        return "Ce probleme a été déjà resolu";
                    }

                }
            } else {//authentification echoué ou problème n' pas été posté par l'utilisateur

                System.out.println("Acces refusé");
                return "Acces refusé";
            }
        } else {//Si le problème n'existe pas

            System.out.println("Ce problème n'existe pas");
            return "Ce problème n'existe pas";
        }

    }

    @ApiOperation(value = "Controller qui permet de commenter une solution")
    @PostMapping("/createCommentaire/{email}/{password}/{titreProbleme}")
    public String createCommentaire(@RequestBody Commentaire commentaire, @PathVariable String
            email, @PathVariable String password, @PathVariable String titreProbleme) {

        //Authentification
        if (servicesUsers.connexion(email, password) != null) {

            //recupere le probleme correspondant au titre mis à l'url
            Probleme probleme = servicesUsers.trouverProblemeParTitre(titreProbleme);

            //verifie si le probleme existe ou pas
            if (probleme != null) {
                if (commentaire.getContenu() != null){
                    //recupere l'id du probleme
                    Long idProbleme = probleme.getId();

                    //recupere la solution correspondant au probleme
                    Solution solution = servicesUsers.trouverSolutionParIdProbleme(idProbleme);

                    //recuperation de l'user par son email
                    Compte compteUser = servicesUsers.trouverCompteParEmail(email);

                    //recuperation de l'utilisateur par son compte
                    Utilisateur user = servicesUsers.trouverUtilisateurParCompte(compteUser);

                    //c reation du commentaire
                    servicesUsers.creerCommentaire(commentaire, user, solution);

                    System.out.println("Commentaire enregistré avec succes");
                    return "Commentaire enregistré avec succes";
                } else {//si on trouve pas le probleme

                    System.out.println("Veuillez mettre un commentaire");
                    return "Veuillez mettre un commentaire";
                }
                }else {

                    System.out.println("Veuillez saisir quelques choses");
                    return "Veuillez saisir quelques choses";
                }
        } else {//au cas ou l'authentification echouera

            System.out.println("Acces refusé");
            return "Acces refusé";
        }

    }

    //modification de la solution
    @ApiOperation(value = "Controller qui permet de modifier une solution")
    @PutMapping("/updateSolution/{email}/{password}/{titreProbleme}")
    public String updateSolution(@RequestBody Solution solution, @PathVariable String
            email, @PathVariable String password, @PathVariable String titreProbleme) {

        //si l'email et password de l'user sont correct
        if (servicesUsers.connexion(email, password) != null) {

            Probleme p = servicesUsers.trouverProblemeParTitre(titreProbleme);

            if(p != null) {

                //recuperation de l'id du problème
                Long idPro = p.getId();

                Solution s = servicesUsers.trouverSolutionParIdProbleme(idPro);

                if (s != null){
                    Long idSolution = s.getId();

                    //recupere l'id de l'utilisateur qui a posté le problème
                    Long id_userProbl = servicesUsers.trouverProblemeParId(idPro).getUtilisateur().getId();

                    //Recuperation du compte de l'utilisateur qui veut resoudre le problème par son email
                    Compte cpte_user = servicesUsers.trouverCompteParEmail(email);

                    //recuperation de l'id de l'user qui veut poster une solution
                    Long id_userSolution = servicesUsers.trouverUtilisateurParCompte(cpte_user).getId();

                    if(id_userProbl.equals(id_userSolution) || cpte_user.getRole().equals("admin")){

                        if(p.getEtat().getNom().equals("Fermé")){

                            System.out.println("Ce problème a été fermé");
                            return "Ce problème a été fermé";
                        }else {
                            servicesUsers.modifierSolution(idSolution, solution);

                            System.out.println("Solution modifier avec succes");
                            return "Solution modifier avec succes";
                        }
                    }else {

                        System.out.println("Ce problème n'a été posté par toi");
                        return "Ce problème n'a été posté par toi";
                    }

                }else {

                    System.out.println("Cette solution n'existe");
                    return "Cette solution n'existe";
                }
            }else {

                System.out.println("Ce probleme n'existe pas");
                return "Ce probleme n'existe pas";
            }

        } else {

            System.out.println("Acces interdit");
            return "Acces interdit";
        }
    }

    //modification du probleme
    @ApiOperation(value = "Controller qui permet de modifier un problème")
    @PutMapping("/modifierProbleme/{email}/{password}/{titre}")
    public String modifierProbleme(@RequestBody Probleme probleme,@PathVariable String email, @PathVariable String password ,@PathVariable String titre){


        //verifier l'email et le mot de passe
        if(servicesUsers.connexion(email, password) != null) {

            //recupere le probleme à modifier par son titre
            Probleme problemeAmodifier = servicesUsers.trouverProblemeParTitre(titre);

            //recuperation de l'user qui veut modifier par son email
            Compte compteUser = servicesUsers.trouverCompteParEmail(email);

            //verifie si le problème existe ou pas
            if (problemeAmodifier != null){

                //L'id de la personne qui a posté le problème
                Long idUserPoster = problemeAmodifier.getUtilisateur().getId();

                //l'id de l'user qui veut modifier le probleme
                Long idUser = servicesUsers.trouverUtilisateurParCompte(compteUser).getId();


                //verifie si l'user posteur et l'user modificateur sont égaux ou si l'user est un admin
                if(idUser.equals(idUserPoster) || compteUser.getRole().equals("admin")){

                    //verifie si l'etat du probleme, un problème fermé ne peut être modifier
                    if(problemeAmodifier.getEtat().getNom().equals("Fermé")){

                        System.out.println("Ce problème a été fermé");
                        return "Ce problème a été fermé";
                    }else {//lorsque l'etat du probleme est different de fermé

                        //on modifie le problème
                        servicesUsers.modifierProbleme(probleme, problemeAmodifier.getId());

                        System.out.println("Modification reussi");
                        return "Modification reussi";
                    }
                }else {//lorsque les deux id verifier sont different

                    System.out.println("Ce probleme n'a pas été posté par toi");
                    return "Ce probleme n'a pas été posté par toi";
                }
            }else{//lorsque le problemeAmodier est vide

                System.out.println("Ce probleme n'existe pas");
                return "Ce probleme n'existe pas";
            }

        }else{//authentification échoué

            System.out.println("Acces refusé");
            return "Acces refusé";
        }
    }

    //modification du commentaire
    @ApiOperation(value = "Controller qui permet de modifier un commentaire")
    @PutMapping("/modifierCommentaire/{email}/{password}/{id}")
    public String mofifierCommentaire(@RequestBody Commentaire commentaire, @PathVariable String email, @PathVariable String password, @PathVariable Long id){

        if (servicesUsers.connexion(email, password) != null){

            //le commentaire à modifier
            Commentaire commentaireAmodif = servicesUsers.trouverCommentaireParId(id);

            //verifie si le commentaire que l'user veut modier exite ou pas
            if (commentaireAmodif != null){

                //l'identifiant du commentaire à modifier
                Long idCommentaire = commentaireAmodif.getId();

                //Compte de l'utilisateur qui veut modifier le probleme
                Compte compteCommentaireModif = servicesUsers.trouverCompteParEmail(email);

                //liditifiant de l'user qui veut faire la modifiacation
                Long id_user_commentaire_modif = servicesUsers.trouverUtilisateurParCompte(compteCommentaireModif).getId();

                //l'identifiant de l'utilisateur qui poster le commentaire
                Long id_user_commentaire_poster = commentaireAmodif.getUtilisateur().getId();

                //verifie si l'id de celui qui a posté est égale à l'id celui qui veut commenter la solution
                if (id_user_commentaire_poster.equals(id_user_commentaire_modif) || compteCommentaireModif.getRole().equals("admin")){

                    //Modification du commentaire
                    servicesUsers.modifierCommentaire(commentaire, idCommentaire);

                    System.out.println("Commentaire modifier avec succes");
                    return "Commentaire modifier avec succes";
                }else {//lorsque les deux id verifier sont differnt

                    System.out.println("Ce commentaire n'a pas été posté par toi");
                    return "Ce commentaire n'a pas été posté par toi";
                }
            }else {//lorsque le demandé n'existe pas

                System.out.println("Cette commentaire n'existe pas");
                return "Cette commentaire n'existe pas";
            }
        }else {//authentification échoué

            System.out.println("Accès refusé");
            return "Accès refusé";
        }

    }

}
