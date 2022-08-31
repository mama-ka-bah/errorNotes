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

    @Override
    public String suprimerProbleme(Long probleme_id, Long solution_id) {
        repositorySolution.deleteById(solution_id);
        repositoryProbleme.deleteById(probleme_id);
        return "solution supprimé avec succes";
    }

    @Override
    public String supprimerLesCommentairesSolution(Solution solution) {
        repositoryCommentaire.deleteBySolution(solution);
        return "commentaire supprimé";
    }

}
