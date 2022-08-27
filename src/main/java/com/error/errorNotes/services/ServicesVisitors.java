package com.error.errorNotes.services;

import com.error.errorNotes.model.Compte;
import com.error.errorNotes.model.Utilisateur;

public interface ServicesVisitors {

    Utilisateur creerCompteUser(Utilisateur utilisateur, String email, String password);
    Compte trouverCompteParEmail(String email);
}
