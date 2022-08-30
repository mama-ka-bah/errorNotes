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

<<<<<<< HEAD
    List<Probleme_technologies> afficherProblemeTechnologies();

    Probleme_technologies trouverProbleme_technologiesParProbleme(Probleme probleme);

    Object trouverProbleme_technologieParTitreProbleme(String titre);
=======
    List<Technologie> lireTechnologie();
>>>>>>> 39142c011fe2f063dae436cf640a608a9f580c39

}
