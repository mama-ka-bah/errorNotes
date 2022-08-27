package com.error.errorNotes.services;

<<<<<<< HEAD
import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;

public interface ServicesAdmins{

    Etat creerEtat(Etat etat);

    Technologie creerTechnologie(Technologie technologie);

    String supprimer(long id);
=======
import com.error.errorNotes.model.Utilisateur;

public interface ServicesAdmins{
    Utilisateur creerCompteAdmin(Utilisateur utilisateur, String email, String password);
>>>>>>> authentification
}
