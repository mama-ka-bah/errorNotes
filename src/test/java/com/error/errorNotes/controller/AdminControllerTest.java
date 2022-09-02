package com.error.errorNotes.controller;

import com.error.errorNotes.model.*;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesVisitors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminControllerTest {

    @Autowired
    private AdminController adminController;

    @Test
    void creerCompteAdmin() {

        Compte compte = new Compte();
        Utilisateur utilisateur = new Utilisateur();

        String email = "kmahamadou858@gmail.com";
        String password = "keita123@";
        utilisateur.setAdresse("Bamako");
        utilisateur.setNom("Mahamadou");
        utilisateur.setPrenom("Keita");
        compte.setEmail("9kmahamadou858@gmail.com");
        compte.setPassword("keita123@");

        utilisateur.setCompte(compte);

        adminController.creerCompteAdmin(utilisateur, email, password);

    }

    @Test
    void createEtat() {
        Etat etat = new Etat();
        etat.setNom("Resolus");

        String email = "kmahamadou858@gmail.com";
        String password = "keita123@";

        adminController.createEtat(etat,email, password );
    }

    @Test
    void createTechnologie() {
        Technologie technologie = new Technologie();

        String email = "amadou@gmail.com";
        String password = "diadje123@";
        technologie.setNom("JavaScript");

        adminController.createTechnologie(technologie, email, password);
    }

    @Test
    void deleteCommentaire() {
        Commentaire commentaire = new Commentaire();

        String email = "kmahamadou858@gmail.com";
        String password = "keita123@";
        Long id = 3L;

        adminController.deleteCommentaire(email,password, id);
    }

    @Test
    void supprimerProbleme() {

        String email = "kmahamadou858@gmail.com";
        String password = "keita123@";
        String titre = "Test 27";

        adminController.supprimerProbleme(email, password, titre);
    }
}