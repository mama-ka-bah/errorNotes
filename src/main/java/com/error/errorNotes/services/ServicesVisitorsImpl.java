package com.error.errorNotes.services;

import com.error.errorNotes.model.Compte;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.repository.RepositoryCompte;
import com.error.errorNotes.repository.RepositoryUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesVisitorsImpl implements ServicesVisitors{

   final RepositoryUtilisateur repositoryUtilisateur;
   final RepositoryCompte repositoryCompte;
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
    public Compte trouverCompteParEmail(String email) {
        return repositoryCompte.findByEmail(email);
    }

}
