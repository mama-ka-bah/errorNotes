package com.error.errorNotes.controller;

import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.repository.RepositoryEtat;
import com.error.errorNotes.repository.RepositoryUtilisateur;
import com.error.errorNotes.services.ServicesUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private ServicesUsers servicesUsers;

    @Autowired
    private RepositoryUtilisateur repositoryUtilisateur;

    @Autowired
    private RepositoryEtat repositoryEtat;


    @Test
    void createProbleme() {
        Probleme probleme = new Probleme();
        Etat etat = new Etat();
        Utilisateur user = new Utilisateur();


        user = repositoryUtilisateur.findById(1L).get();
        etat = repositoryEtat.findById(1L).get();


        probleme.setTitre("Titre 1");
        probleme.setEtat(etat);
        probleme.setDescpt("description de mon probleme");
       // probleme.set



        probleme.setUtilisateur(user);
        //probleme.setEtat();


       //servicesUsers.creerProbleme();

    }

    @Test
    void createSolution() {
    }

    @Test
    void createCommentaire() {
    }

    @Test
    void updateSolution() {
    }

    @Test
    void modifierProbleme() {
    }

    @Test
    void mofifierCommentaire() {
    }
}