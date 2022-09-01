package com.error.errorNotes.services;

import com.error.errorNotes.model.Compte;
import com.error.errorNotes.repository.RepositoryCompte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class ServicesVisitorsImplTest {

    @Autowired
    private ServicesVisitors servicesVisitors;

    @Autowired
    private RepositoryCompte repositoryCompte;




    @Test
    public void creerCompteUser() {
        Compte compte = new Compte();
        compte.setId(1L);
        compte.setRole("user");
        compte.setPassword("12345");
        compte.setEmail("kmahamadou858@gmail.com");
        repositoryCompte.save(compte);


    }

    @Test
    public void lireProbleme() {

    }

    @Test
    public void lireCommentaire() {
    }

    @Test
    public void lireSolution() {
    }

    @Test
    public void afficherProblemeTechnologies() {
    }

    @Test
    void trouverProbleme_technologiesParProbleme() {
    }

    @Test
    public void trouverProbleme_technologieParTitreProbleme() {
    }

    @Test
    public void trouverProbleme_technologieParTitreProblemeSolution() {
    }

    @Test
    public void lireTechnologie() {
    }
}