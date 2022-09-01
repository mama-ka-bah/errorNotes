package com.error.errorNotes.services;


import com.error.errorNotes.model.*;
import org.springframework.stereotype.Service;

@Service
public interface ServicesAdmins{

    //Methode qui permet de créer les états
    Etat creerEtat(Etat etat, String email);

    //Methode qui permet de créer les technologies
    Technologie creerTechnologie(Technologie technologie, String email);

    //Methode qui permet de supprimer les commentaires d'une solution
    String supprimer(long id);

    //Methode qui permet de créer un compte admin
    Utilisateur creerCompteAdmin(Utilisateur utilisateur, String email, String password);

    //Methode qui permet de récupérer un état par son nom
    Etat TrouverEtatparNom(String nom);

    String suprimerProbleme(Long probleme_id, Long solution_id);

    String supprimerLesCommentairesSolution(Solution solution);

}
