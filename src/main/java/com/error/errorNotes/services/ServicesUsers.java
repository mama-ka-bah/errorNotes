package com.error.errorNotes.services;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;

public interface ServicesUsers {

    Probleme creerProbleme(Probleme probleme);

    Solution creerSolution(Solution solution, Probleme prob);

    Commentaire creerCommentaire(Commentaire commentaire);

  //  Etat creerEtat(Etat etat);
    Boolean connexion(String email, String password);

    Probleme trouverProblemeParTitre(String titre);

    Solution trouverSolutionParIdProbleme(Long problemeId);

}
