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

@Api(value = "hello", description = "Sample hello world application")
@RestController
@RequestMapping("/visitor")
@AllArgsConstructor
public class VisitorController {

    final private ServicesVisitors servicesVisitors;
    final private ServicesUsers servicesUsers;

    final private RepositoryProbleme repositoryProbleme;
    final private RepositoryProblemeTechnologie repositoryProblemeTechnologie;

    //methode permettant de creer un compte utilisateur
    @ApiOperation(value = "Just to test the sample test api of My App Service")
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

    @ApiOperation(value = "Just to test the sample test api of My App Service")
    @GetMapping("/afficherProbleme")
    public List<Probleme> readProbleme(){

        return servicesVisitors.lireProbleme();
    }

    @GetMapping("/afficherSolution")
    public List<Solution> readSolution(){


        return servicesVisitors.lireSolution();
    }

    @GetMapping("/afficherCommentaire")
    public List<Commentaire> readCommentaire(){


        return servicesVisitors.lireCommentaire();
    }

    @GetMapping("/afficherProTchno")
    public List<Probleme_technologies> afficherProblemeTechnologies(){

        return servicesVisitors.afficherProblemeTechnologies();
    }

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

            //recuperation du probleme
            Object pro = servicesVisitors.trouverProbleme_technologieParTitreProbleme(titre);

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

    @GetMapping("/afficherUnProbleme/{titre}")
    public Probleme afficherUnProblemeDonnée(@PathVariable String titre){

        return servicesUsers.trouverProblemeParTitre(titre);
    }

}

/*
afficher un probleme donnée avec sa solution et les commentaires sur la solution
ajouter la solution au resultat de la recherche
faire la recherce sur les tables solution et commentaire en meme que sur les tables probleme et techono probleme
afficher les commentaire d'une solution
 */
