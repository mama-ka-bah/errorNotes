package com.error.errorNotes.services;


import com.error.errorNotes.model.*;
import com.error.errorNotes.repository.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesAdminsImpl implements ServicesAdmins{

    private final RepositoryEtat repositoryEtat;
    private final RepositoryTechnologie repositoryTechnologie;
    private final RepositoryCommentaire repositoryCommentaire;
    private final RepositoryUtilisateur repositoryUtilisateur;
    private final RepositoryCompte repositoryCompte;
    private final RepositoryProbleme repositoryProbleme;
    private final RepositorySolution repositorySolution;

    @Override
    public Etat creerEtat(Etat etat) {

        return repositoryEtat.save(etat);
    }

    @Override
    public Technologie creerTechnologie(Technologie technologie, String email) {

        return repositoryTechnologie.save(technologie);
    }

    @Override
    public String supprimer(long id) {
        repositoryCommentaire.deleteById(id);
        return "Commentaire supprimer";
    }

    @Override
    public Utilisateur creerCompteAdmin(Utilisateur utilisateur) {

        utilisateur.getCompte().setRole("admin");

        return repositoryUtilisateur.save(utilisateur);
    }

    @Override
    public Etat TrouverEtatparNom(String nom) {
        return repositoryEtat.findByNom(nom);
    }

    @Override
    public String suprimerProbleme(Long probleme_id) {
        repositoryProbleme.deleteById(probleme_id);
        return "solution supprimé avec succes";
    }

    @Override
    public String suprimerSolution(Long solution_id) {
        repositorySolution.deleteById(solution_id);
        return "solution supprimé avec succes";
    }

    @Override
    public String supprimerLesCommentairesSolution(Solution solution) {
        repositoryCommentaire.deleteBySolution(solution);
        return "commentaire supprimé";
    }

}
