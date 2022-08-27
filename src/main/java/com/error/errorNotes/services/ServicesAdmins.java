package com.error.errorNotes.services;

import com.error.errorNotes.model.Utilisateur;

public interface ServicesAdmins{
    Utilisateur creerCompteAdmin(Utilisateur utilisateur, String email, String password);
}
