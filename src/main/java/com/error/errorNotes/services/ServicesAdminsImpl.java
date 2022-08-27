package com.error.errorNotes.services;

import com.error.errorNotes.model.Compte;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.repository.RepositoryCompte;
import com.error.errorNotes.repository.RepositoryUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesAdminsImpl implements ServicesAdmins{

    private final RepositoryUtilisateur repositoryUtilisateur;
    private final RepositoryCompte repositoryCompte;

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
