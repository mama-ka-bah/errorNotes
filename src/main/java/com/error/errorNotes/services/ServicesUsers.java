package com.error.errorNotes.services;

import com.error.errorNotes.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesUsers {

    Probleme creerProbleme(Probleme probleme, Utilisateur user);

    Solution creerSolution(Solution solution, Probleme prob);

    Commentaire creerCommentaire(Commentaire commentaire, Utilisateur user, Solution solution);

  //  Etat creerEtat(Etat etat);
    Boolean connexion(String email, String password);

    Probleme trouverProblemeParTitre(String titre);

    Solution trouverSolutionParIdProbleme(Long problemeId);

    //Utilisateur trouverUtilisateurParCompteId(Long id);

   Compte trouverCompteParEmail(String email);

   Probleme trouverProblemeParId(Long id);

   Utilisateur trouverUtilisateurParCompte(Compte compte);

    List<Probleme_technologies> enregistrerProblemesTechnologies(List<Probleme_technologies> protechno);

   Technologie trouverTechonologieParNom(String nom);

    List<Ressource> enregistrerRessource(List<Ressource> ressourcee);

    Solution modifierSolution(Long id, Solution solution);

    Probleme modifierProbleme(Probleme probleme, Long id);

    Commentaire modifierCommentaire(Commentaire commentaire, Long id);

    Commentaire trouverCommentaireParId(Long id);

}
