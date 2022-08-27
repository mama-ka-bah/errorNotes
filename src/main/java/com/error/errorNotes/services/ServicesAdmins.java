package com.error.errorNotes.services;


import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.model.Utilisateur;

public interface ServicesAdmins{

    Etat creerEtat(Etat etat);

    Technologie creerTechnologie(Technologie technologie);

    String supprimer(long id);

    Utilisateur creerCompteAdmin(Utilisateur utilisateur, String email, String password);

}
