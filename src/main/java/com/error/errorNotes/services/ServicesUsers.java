package com.error.errorNotes.services;

import com.error.errorNotes.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesUsers {

    //Methode qui permet créer un problème en s'authentifiant
    Probleme creerProbleme(Probleme probleme, Utilisateur user);

    //Methode qui permet créer la solution du problème en s'authentifiant et en se basant sur le titre du probème
    Solution creerSolution(Solution solution, Probleme prob);

    //Methode qui permet de commenter une solution en s'authentifiant
    Commentaire creerCommentaire(Commentaire commentaire, Utilisateur user, Solution solution);

    //Methode qui permet de se connecter par email et password
  //  Etat creerEtat(Etat etat);
    Boolean connexion(String email, String password);

    //Methode qui permet de retrouver le problème par le titre
    Probleme trouverProblemeParTitre(String titre);

    //Methode qui permet de retrouver la solution par la clé étrangère du problème
    Solution trouverSolutionParIdProbleme(Long problemeId);

    //Utilisateur trouverUtilisateurParCompteId(Long id);

    //Methode qui permet de retrouver le compte par email
   Compte trouverCompteParEmail(String email);

    //Methode qui permet de retouver le problème par son identifiant
   Probleme trouverProblemeParId(Long id);

    //Methode qui permet de retrouver l'utilisateur par son compte
   Utilisateur trouverUtilisateurParCompte(Compte compte);

    List<Probleme_technologies> enregistrerProblemesTechnologies(List<Probleme_technologies> protechno);

   Technologie trouverTechonologieParNom(String nom);

    List<Ressource> enregistrerRessource(List<Ressource> ressourcee);

    Solution modifierSolution(Long id, Solution solution);

    Probleme modifierProbleme(Probleme probleme, Long id);

    Commentaire modifierCommentaire(Commentaire commentaire, Long id);

    Commentaire trouverCommentaireParId(Long id);

    void supprimerRessourceParIdSolution(Long idSolution);

    void supprimerProblemeTechnologie(Long idProbleme);

}
