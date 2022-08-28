package com.error.errorNotes.services;


import com.error.errorNotes.model.*;
import com.error.errorNotes.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@AllArgsConstructor
public class ServicesUserImpl implements ServicesUsers{

    private final RepositoryProbleme repositoryProbleme;
    private final RepositoryCommentaire repositoryCommentaire;
    private final RepositorySolution repositorySolution;
    private final RepositoryUtilisateur repositoryUtilisateur;
    private final RepositoryCompte repositoryCompte;
    private final RepositoryTechnologie repositoryTechnologie;
    private final RepositoryProblemeTechnologie repositoryProblemeTechnologie;
    private final RepositoryRessource repositoryRessource;

    @Override
    public Probleme creerProbleme(Probleme probleme, Utilisateur user) {
        //definition de l'etat
        Etat etat = new Etat();

        //on met l'etat à létat initial
        etat.setId(1L);

        //on attribue cet etat au problème
        probleme.setEtat(etat);

        //On attribue user recuperer en parametre au problème
        probleme.setUtilisateur(user);

        //on attribue la date actuelle au problème
        probleme.setDate(new Date());

        //On enregistre le problème
        return repositoryProbleme.save(probleme);
    }

    @Override
    public Solution creerSolution(Solution solution, Probleme prob) {
        //instaciation du probleme
        Probleme probleme = new Probleme();

        //Instaciation de l'etat
        Etat etat = new Etat();

        //Attribution de l'id 2(fermé) à l'etat
        etat.setId(2L);

        //On met le probleme à l'état fermé
        prob.setEtat(etat);

        //definition du probleme conserné de la solution
        solution.setProbleme(prob);

        return repositorySolution.save(solution);
    }

    @Override
    public Commentaire creerCommentaire(Commentaire commentaire, Utilisateur user, Solution solution) {

        //attribution de de la date actuelle au commentaire
        commentaire.setDate(new Date());

        //attribution de l'user au commentaire
        commentaire.setUtilisateur(user);

        //attribution de la solution au probleme
        commentaire.setSolution(solution);

        //enregistrement du commentaire
        return repositoryCommentaire.save(commentaire);
    }

    /*@Override
    public Etat creerEtat(Etat etat) {
        return repositoryEtat.save(etat);
    }*/

    //fonction utilisé pour verfier si l'user a un compte pas et si son password est correct
    @Override
    public Boolean connexion(String email, String password) {

        //on se sert de l'email de l'user pour recuperer son compte
        Compte compte = repositoryCompte.findByEmail(email);

        //on verfie si son compte a été retrouvé ou pas, et si son password est correct également
        if(compte != null && compte.getPassword().equals(password)){
            System.out.println("Connexion éffectuée avec succes");
            return true;
        }else {//lorsque son compte n'a pas été retrouvé
            System.out.println("ce compte n'existe pas");
            return false;
        }
    }

    @Override
    public Probleme trouverProblemeParTitre(String titre) {
        return repositoryProbleme.findByTitre(titre);
    }

    @Override
    public Solution trouverSolutionParIdProbleme(Long problemeId) {
        return repositorySolution.FIND_SOLUTION_PAR_ID_PROBLEME(problemeId);
    }

    @Override
    public Compte trouverCompteParEmail(String email) {

        return repositoryCompte.findByEmail(email);
    }

    @Override
    public Probleme trouverProblemeParId(Long id) {
        return repositoryProbleme.findById(id).get();
    }

    @Override
    public Utilisateur trouverUtilisateurParCompte(Compte compte) {
        return repositoryUtilisateur.findByCompte(compte);
    }

    @Override
    public List<Probleme_technologies> enregistrerProblemesTechnologies(List<Probleme_technologies> protechno) {
        return repositoryProblemeTechnologie.saveAll(protechno);
    }

    @Override
    public Technologie trouverTechonologieParNom(String nom) {
        return repositoryTechnologie.findByNom(nom);
    }

    @Override
    public List<Ressource> enregistrerRessource(List<Ressource> ressource) {
        return repositoryRessource.saveAll(ressource);
    }

}
