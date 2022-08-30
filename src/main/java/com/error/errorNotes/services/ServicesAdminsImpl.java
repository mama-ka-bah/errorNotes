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
    public Etat creerEtat(Etat etat, String email) {

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
    public Utilisateur creerCompteAdmin(Utilisateur utilisateur, String email, String password) {

        //on instancie la classe compte
        Compte compte = new Compte();

        //on attribue au compte instacier l'email donné
        compte.setEmail(email);

        //on attribue également au compte instacier
        compte.setPassword(password);

        //on lui attribue le role admin
        compte.setRole("admin");

        //On associe le compte manupilé ci-dessus au l'utilisateur
        utilisateur.setCompte(compte);

        //on enregistre le compte de l'utilisateur
        repositoryCompte.save(compte);

        //on enregistre l'utilisateur lui-meme
        return repositoryUtilisateur.save(utilisateur);
    }

    @Override
    public Etat TrouverEtatparNom(String nom) {
        return repositoryEtat.findByNom(nom);
    }

}
