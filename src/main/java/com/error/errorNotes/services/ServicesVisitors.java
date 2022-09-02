package com.error.errorNotes.services;

import com.error.errorNotes.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesVisitors {

    //Methode qui permet de créer un compte utilisateur
    Utilisateur creerCompteUser(Utilisateur utilisateur);

    List<Probleme> lireProbleme();

    List<Commentaire> lireCommentaire();

    List<Solution> lireSolution();

    //Methode qui permet d'afficher les problèmeTechnologies
    List<Probleme_technologies> afficherProblemeTechnologies();

    Probleme_technologies trouverProbleme_technologiesParProbleme(Probleme probleme);

    //Methode qui permet de retrouver les problèmeTechnologies par le titre du problème
    Object trouverProbleme_technologieParTitreProbleme(String titre);

    //Methode qui permet de retrouver les problèmeTechnologies par le titre du problème solution
    Object trouverProbleme_technologieParTitreProblemeSolution(String titre);

    List<Technologie> lireTechnologie();

}
