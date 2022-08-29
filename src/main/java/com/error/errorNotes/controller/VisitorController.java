package com.error.errorNotes.controller;

import com.error.errorNotes.model.*;
import com.error.errorNotes.repository.RepositoryProbleme;
import com.error.errorNotes.repository.RepositoryProblemeTechnologie;
import com.error.errorNotes.services.ServicesUsers;
import com.error.errorNotes.services.ServicesVisitors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
       if(servicesUsers.trouverCompteParEmail(utilisateur.getCompte().getEmail()) == null){
           if(utilisateur.getCompte().getEmail().trim().equals("") || utilisateur.getCompte().getPassword().trim().equals("")){
               return "Veuillez remplir les champs obligatoire";
           }else {
               if (utilisateur.getCompte().getPassword().length() >= 8){
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

        List<Probleme_technologies> tousProblemesTechnologies = servicesVisitors.afficherProblemeTechnologies();

        //int taille = tousProblemesTechnologies.size();
        //List<ArrayList<Probleme_technologies>> listAretourner = new ArrayList<>(taille);

        List<Probleme_technologies> listAretourner = new ArrayList<>();

        /*
        for(int i=0; i < taille; i++) {
            listAretourner.add(new ArrayList());
        }
         */

        //String[][] titre_occurence = new String[taille][1];

        String[] tabMots = motsCles.split(" ");
        Map<String, Integer> map = new HashMap<>();

        for(Probleme_technologies pt: tousProblemesTechnologies){
            int occu = 0;
            for (String mc: tabMots){
                if(pt.getProblemet().getTitre().contains(mc)){
                    occu += StringUtils.countMatches(pt.getProblemet().getTitre(), mc);
                }
                if (pt.getProblemet().getDescpt().contains(mc)){
                    occu += StringUtils.countMatches(pt.getProblemet().getDescpt(), mc);
                }
                if (pt.getTechno().getNom().contains(mc)){
                    occu += StringUtils.countMatches(pt.getTechno().getNom(), mc);
                }
            }

            if(occu > 0)
            map.put(pt.getProblemet().getTitre(), occu);

        }


      List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
        //List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
        List<Object> listObjectAretourner = new ArrayList<>();
    for (Map.Entry<String, Integer> item: list){

        String titre = item.getKey();

        //Probleme pro = servicesUsers.trouverProblemeParTitre(titre);
        Object pro = repositoryProblemeTechnologie.FIND_PROBLEME_TECHNO_ETAT_PAR_TITRE(titre);

       //Probleme_technologies proTchno = servicesVisitors.trouverProbleme_technologiesParProbleme(pro);

        listObjectAretourner.add(pro);

       //listAretourner.add(proTchno);

        System.out.println(titre);
    }
/*
        System.out.println(map.toString());
        // Récupérer les valeurs et les clés
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Clé: " + key + ", Valeur: " + value);
        }

 */
       Collections.reverse(listObjectAretourner);
       //Collections.reverse(listAretourner);

        return listObjectAretourner;
    }
}
