package com.error.errorNotes.services;

import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.repository.RepositoryEtat;
import com.error.errorNotes.repository.RepositoryUtilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicesUserImplTest {

    @Autowired
    private RepositoryUtilisateur repositoryUtilisateur;

    @Autowired
    private RepositoryEtat repositoryEtat;


    @Test
    void creerProbleme() {

        Probleme probleme = new Probleme();
        Etat etat = new Etat();
        Utilisateur user = new Utilisateur();


        user = repositoryUtilisateur.findById(1L).get();
        etat = repositoryEtat.findById(1L).get();


        probleme.setTitre("Titre 1");
        probleme.setEtat(etat);
        probleme.setDescpt("description de mon probleme");
    }

    @Test
    void creerSolution() {

    }

    @Test
    void creerCommentaire() {
    }

    @Test
    void connexion() {
    }

    @Test
    void trouverProblemeParTitre() {
    }

    @Test
    void trouverSolutionParIdProbleme() {
    }

    @Test
    void trouverCompteParEmail() {
    }

    @Test
    void trouverProblemeParId() {
    }

    @Test
    void trouverUtilisateurParCompte() {
    }

    @Test
    void enregistrerProblemesTechnologies() {
    }

    @Test
    void trouverTechonologieParNom() {
    }

    @Test
    void enregistrerRessource() {
    }

    @Test
    void modifierSolution() {
    }

    @Test
    void modifierProbleme() {
    }

    @Test
    void modifierCommentaire() {
    }

    @Test
    void trouverCommentaireParId() {
    }

    @Test
    void supprimerRessourceParIdSolution() {
    }

    @Test
    void supprimerProblemeTechnologie() {
    }
}