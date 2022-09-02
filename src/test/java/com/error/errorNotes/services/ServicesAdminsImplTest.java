package com.error.errorNotes.services;

import com.error.errorNotes.model.Compte;
import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.repository.RepositoryCompte;
import com.error.errorNotes.repository.RepositoryProbleme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServicesAdminsImplTest {

    @Autowired
    private ServicesAdmins servicesAdmins;

    @Autowired
    private RepositoryProbleme repositoryProbleme;

    @Autowired
    private RepositoryCompte repositoryCompte;

    @Test
    void creerEtat() {
        Etat etat = new Etat();
        etat.setNom("Initié");
        etat.setId(1L);

      //  servicesAdmins.creerEtat(etat, "kmahamadou858@gmail.com");

    }

    @Test
    void creerTechnologie() {
        Technologie tech = new Technologie();

        tech.setId(1L);
        tech.setNom("PHP");

        servicesAdmins.creerTechnologie(tech, "kmahamadou858@gmail.com");


    }

    @Test
    void supprimer() {
    }

    @Test
    void creerCompteAdmin() {
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setRole("admin");
        compte.setPassword("12345");
        compte.setEmail("kmahamadou858@gmail.com");
        repositoryCompte.save(compte);
    }

    @Test
    void trouverEtatparNom() {
    }

    @Test
    void suprimerProbleme() {
    }

    @Test
    void supprimerLesCommentairesSolution() {
    }
}