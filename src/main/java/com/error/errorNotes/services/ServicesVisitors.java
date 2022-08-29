package com.error.errorNotes.services;

import com.error.errorNotes.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicesVisitors {

    Utilisateur creerCompteUser(Utilisateur utilisateur, String email, String password);

    List<Probleme> lireProbleme();

    List<Commentaire> lireCommentaire();

    List<Solution> lireSolution();

    List<Probleme_technologies> afficherProblemeTechnologies();

    Probleme_technologies trouverProbleme_technologiesParProbleme(Probleme probleme);

    Object trouverProbleme_technologieParTitreProbleme(String titre);

}
