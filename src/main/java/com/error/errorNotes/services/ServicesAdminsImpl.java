package com.error.errorNotes.services;


import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.repository.RepositoryCommentaire;
import com.error.errorNotes.repository.RepositoryEtat;
import com.error.errorNotes.repository.RepositoryTechnologie;
import com.error.errorNotes.model.Compte;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.repository.RepositoryCompte;
import com.error.errorNotes.repository.RepositoryUtilisateur;

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

    @Override
    public Etat creerEtat(Etat etat) {

        return repositoryEtat.save(etat);
    }

    @Override
    public Technologie creerTechnologie(Technologie technologie) {

        return repositoryTechnologie.save(technologie);
    }

    @Override
    public String supprimer(long id) {
        repositoryCommentaire.deleteById(id);
        return "Commentaire supprimer";
    }

    @Override
    public Utilisateur creerCompteAdmin(Utilisateur utilisateur, String email, String password) {
        Compte compte = new Compte();
        compte.setEmail(email);
        compte.setPassword(password);
        compte.setRole("admin");
        utilisateur.setCompte(compte);
        repositoryCompte.save(compte);
        return repositoryUtilisateur.save(utilisateur);
    }

}
