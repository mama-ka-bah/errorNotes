package com.error.errorNotes.services;

import com.error.errorNotes.model.*;
import com.error.errorNotes.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicesVisitorsImpl implements ServicesVisitors{

    final RepositoryProbleme repositoryProbleme;
    final RepositoryCommentaire repositoryCommentaire;
    final RepositorySolution repositorySolution;

   final RepositoryUtilisateur repositoryUtilisateur;
   final RepositoryCompte repositoryCompte;

   final RepositoryProblemeTechnologie repositoryProblemeTechnologie;

   final RepositoryTechnologie repositoryTechnologie;
   // Compte compte = new Compte();

    //Implementation de la methode permettant de créer un compte utilisateur
    @Override
    public Utilisateur creerCompteUser(Utilisateur utilisateur, String email, String password) {
        Compte compte = new Compte();
        compte.setEmail(email);
        compte.setPassword(password);
        compte.setRole("user");
        repositoryCompte.save(compte);
        utilisateur.setCompte(compte);
        return repositoryUtilisateur.save(utilisateur);
    }

    @Override
    public List<Probleme> lireProbleme() {

        return repositoryProbleme.findAll();
    }

    @Override
    public List<Commentaire> lireCommentaire() {

        return repositoryCommentaire.findAll();
    }

    @Override
    public List<Solution> lireSolution() {

        return repositorySolution.findAll();
    }

    @Override

    public List<Probleme_technologies> afficherProblemeTechnologies() {

        return repositoryProblemeTechnologie.findAll();
    }

    @Override
    public Probleme_technologies trouverProbleme_technologiesParProbleme(Probleme probleme) {
        return repositoryProblemeTechnologie.findByProblemet(probleme);
    }

    @Override
    public Object trouverProbleme_technologieParTitreProbleme(String titre) {
        return repositoryProblemeTechnologie.FIND_PROBLEME_TECHNO_ETAT_PAR_TITRE(titre);
    }

    @Override
    public Object trouverProbleme_technologieParTitreProblemeSolution(String titre) {
        return repositoryProblemeTechnologie.FIND_PROBLEME_TECHNO_ETAT_PAR_TITRE_SOLUTION(titre);
    }

    public List<Technologie> lireTechnologie() {

        return repositoryTechnologie.findAll();
    }

/*
        public Compte trouverCompteParEmail(String email) {

        return repositoryCompte.findByEmail(email);
    }

 */

}
