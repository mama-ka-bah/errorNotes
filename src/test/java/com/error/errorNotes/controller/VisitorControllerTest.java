package com.error.errorNotes.controller;

import com.error.errorNotes.model.Compte;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VisitorControllerTest {

    @Autowired
    private VisitorController visitorController;

    @Test
    void creerCompte() {
        Compte compte = new Compte();

        Utilisateur utilisateur = new Utilisateur();

        String email = "kmahamadou858@gmail.com";
        String password = "keita123@";

        utilisateur.setAdresse("Bamako");
        utilisateur.setNom("Mahamadou");
        utilisateur.setPrenom("Keita");

        compte.setEmail("6kmahamadou858@gmail.com");
        compte.setPassword("keita123@");

        utilisateur.setCompte(compte);

        visitorController.creerCompte(utilisateur);


    }

    @Test
    void readProbleme() {

       visitorController.readProbleme();
    }

    @Test
    void rechercherProblemeParMotsCles() {

        visitorController.rechercherProblemeParMotsCles("bean 1");
    }

    @Test
    void afficherUnProblemeDonnee() {
        String titre = "Test 25";

        visitorController.afficherUnProblemeDonnee(titre);
    }
}