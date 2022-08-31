package com.error.errorNotes.controller;


import com.error.errorNotes.authers.Recherche;
import com.error.errorNotes.model.*;
import com.error.errorNotes.repository.RepositoryProbleme;
import com.error.errorNotes.repository.RepositoryProblemeTechnologie;
import com.error.errorNotes.services.ServicesUsers;
import com.error.errorNotes.services.ServicesVisitors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.*;


/*
* Le controlleur ci-dessous réagit à des demandes venant des simples visitors
* */

@Api(value = "hello", description = "Les cas d'utilisations pour le visiteur lui seul")
@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorController {

    final private ServicesVisitors servicesVisitors;
    final private ServicesUsers servicesUsers;

    final private RepositoryProbleme repositoryProbleme;
    final private RepositoryProblemeTechnologie repositoryProblemeTechnologie;

    //methode permettant de creer un compte utilisateur
    @ApiOperation(value = "Controller qui permet de créer un compte pour devenir utilisateur")
    @PostMapping("/creerCompte")//{email}/{password}
    public String creerCompte(@RequestBody Utilisateur utilisateur){

        //
       if(servicesUsers.trouverCompteParEmail(utilisateur.getCompte().getEmail()) == null){

           //
           if(utilisateur.getCompte().getEmail().trim().equals("") || utilisateur.getCompte().getPassword().trim().equals("")){

               return "Veuillez remplir les champs obligatoire";
           }else {

               //
               if (utilisateur.getCompte().getPassword().length() >= 8){

                   //
                   servicesVisitors.creerCompteUser(utilisateur, utilisateur.getCompte().getEmail(), utilisateur.getCompte().getPassword());
                   return "Votre compte est créée avec succes";
               }else{
                   return "Le mot de passe doit être superieur à 8 caracteurs";
               }

           }
       }else {
           return "Cet email existe déjà";
       }
    }

    /*@ApiOperation(value = "Permet d'afficher tous les problemes")
    @GetMapping("/afficherProbleme")
    public List<Probleme> readProbleme(){

        return servicesVisitors.lireProbleme();
    }
    /*
        @ApiOperation(value = "Just to test the sample test api of My App Service")
        @GetMapping("/afficherSolution")
        public List<Solution> readSolution(){

            return servicesVisitors.lireSolution();
        }

        @ApiOperation(value = "Just to test the sample test api of My App Service")
        @GetMapping("/afficherCommentaire")
        public List<Commentaire> readCommentaire(){

<<<<<<< HEAD

            return servicesVisitors.lireCommentaire();
        }
    */

    @ApiOperation(value = "Controller qui permet d'afficher les technologies")
    @GetMapping("/afficherProTchno")
    public List<Probleme_technologies> afficherProblemeTechnologies(){

        return servicesVisitors.afficherProblemeTechnologies();
    }

    @ApiOperation(value = "Methodes permettant de rechercher dans la base des mots clés")
    @GetMapping("/rechercherProblemeParMotsCles/{motsCles}")
    public List<Object> rechercherProblemeParMotsCles(@PathVariable String motsCles){

        //recuperation de tous les problemes_technologies de la table probleme_technologies
        List<Probleme_technologies> tousProblemesTechnologies = servicesVisitors.afficherProblemeTechnologies();


        //decoupage des mots d'expression recherché en tableau de mots
        String[] tabMots = motsCles.split(" ");

       Recherche recherche = new Recherche();

        //on declare une d'object à retourner
        List<Object> listObjectAretourner = new ArrayList<>();


        List<Map.Entry<String, Integer>> list = recherche.rechercherProblemeParMotsCles(tousProblemesTechnologies, tabMots);


        //on parcours la liste trier pour recupererr les problemes correspondant
        for (Map.Entry<String, Integer> item: list){

            //on recupere le premier titre
            String titre = item.getKey();

            Object pro = new Object();

            Long idPro = servicesUsers.trouverProblemeParTitre(titre).getId();
            //si le probleme n'a pas de solution
            if (servicesUsers.trouverSolutionParIdProbleme(idPro) == null){
                //recuperation du probleme sans solution
               pro = servicesVisitors.trouverProbleme_technologieParTitreProbleme(titre);

            }else {//si le probleme a une solution on l'affiche avec sa solution

                pro = servicesVisitors.trouverProbleme_technologieParTitreProblemeSolution(titre);
            }



            //on ajoute ce probleme dans la liste à retourner
            listObjectAretourner.add(pro);


            System.out.println(titre);
        }
/*
        // Récupérer les valeurs et les clés
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Clé: " + key + ", Valeur: " + value);
        }

 */
        //on renverse la liste à retourner
        Collections.reverse(listObjectAretourner);

        //retourne la liste des probleme
        return listObjectAretourner;
    }

    @ApiOperation(value = "Affiche un probleme resolu avec sa solution et un probleme non tout sa solution")
    @GetMapping("/afficherUnProbleme/{titre}")
    public List<Object> afficherUnProblemeDonnee(@PathVariable String titre){

        //recupere le probleme correspondant au titre
        Probleme probleme = servicesUsers.trouverProblemeParTitre(titre);

        List<Object> valeur = new ArrayList<>();
        if(probleme != null){
            Solution solution = servicesUsers.trouverSolutionParIdProbleme(probleme.getId());
            if( solution != null){
                valeur.add(solution);
                System.out.println("hjklmù*");
            }else {
                valeur.add(probleme);
            }

        }


        return valeur;
    }


  /*
    @ApiOperation(value = "Permet d'afficher toutes les technologies")
 @ApiOperation(value = "Just to test the sample test api of My App Service")
   @GetMapping("/afficherTechnologie")
    public List<Technologie> readTechnologie(){

        return servicesVisitors.lireTechnologie();
    }*/
}


    /*
    afficher un probleme donnée avec sa solution et les commentaires sur la solution
    ajouter la solution au resultat de la recherche
    faire la recherce sur les tables solution et commentaire en meme que sur les tables probleme et techono probleme
    afficher les commentaire d'une solution
     */
