package com.error.errorNotes.authers;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme_technologies;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.services.ServicesUsers;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@NoArgsConstructor
public class Recherche {
    public  List<Map.Entry<String, Integer>> rechercherProblemeParMotsCles(List<Probleme_technologies> tousProblemesTechnologies, String[] tabMots, ServicesUsers servicesUsers){

       //Declaration d'un hasmap pour stocker les titre des problemes rechercherchés
       Map<String, Integer> map = new HashMap<>();

       //on parcours les problemes_technologies retournés
       for(Probleme_technologies pt: tousProblemesTechnologies){

           //declaration de variable permettant de l'ocurence d'un mot dans un probleme
           int occu = 0;

           Solution solution = servicesUsers.trouverSolutionParIdProbleme(pt.getId());

           //On parcours le tableau des mots
           for (String mc: tabMots){

               //on cherche le mot dans titre
               if(pt.getProblemet().getTitre().contains(mc)){

                   //on incremente l' occurrence du mot de 1 s'il est retrouvé
                   occu += StringUtils.countMatches(pt.getProblemet().getTitre(), mc);

               }

               //Fonctionne comme la première condition mais ici on recherche dans la description du probleme
               if (pt.getProblemet().getDescpt().contains(mc)){
                   occu += StringUtils.countMatches(pt.getProblemet().getDescpt(), mc);
               }

               //Fonctionne comme la première condition mais ici on fait la recherche dans le nom de la technologie
               if (pt.getTechno().getNom().contains(mc)){
                   occu += StringUtils.countMatches(pt.getTechno().getNom(), mc);
               }
               if (solution != null){
                   List<Commentaire> commentaireList = servicesUsers.trouverTousLesComentaireParSolution(solution.getId());
                   if (solution.getContenu().contains(mc)){
                       occu += StringUtils.countMatches(solution.getContenu(), mc);
                       System.out.println("Mon occurence est à : " + occu);
                   }

                   for (Commentaire c: commentaireList){
                       if (c.getContenu() != null){
                           if (c.getContenu().contains(mc)){
                               occu += StringUtils.countMatches(c.getContenu(), mc);
                           }
                       }
                   }

               }
           }

           //si l'occurrence actuelle du probleme actuel est superieure à 0, on l'ajoute l'occurence et le titre du probleme et dans hasmap declaré ci-dessus
           if(occu > 0)
               map.put(pt.getProblemet().getTitre(), occu);

       }


       //declaration de l'hasmap pour stocker l'hasmap trié
       List<Map.Entry<String, Integer>> listOrdonnee = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());

       //cette fonction est utilisée pour trier l'hasmap(map) des titre et occurence
       Collections.sort(listOrdonnee, new Comparator<Map.Entry<String, Integer>>() {

           //on declare o1 et o2 come hasmap
           @Override
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {

               //on fait la comparaison deux à deux pour les ordonnés
               return o1.getValue().compareTo(o2.getValue());
           }
       });
       
       return listOrdonnee;
    }
}
