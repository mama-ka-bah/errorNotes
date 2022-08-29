package com.error.errorNotes.services;


import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.model.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface ServicesAdmins{

    Etat creerEtat(Etat etat, String email);

    Technologie creerTechnologie(Technologie technologie, String email);

    String supprimer(long id);

    Utilisateur creerCompteAdmin(Utilisateur utilisateur, String email, String password);

    Etat TrouverEtatparNom(String nom);

}
