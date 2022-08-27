package com.error.errorNotes.services;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.repository.RepositoryCommentaire;
import com.error.errorNotes.repository.RepositoryEtat;
import com.error.errorNotes.repository.RepositoryProbleme;
import com.error.errorNotes.repository.RepositorySolution;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesUserImpl implements ServicesUsers{

    private final RepositoryProbleme repositoryProbleme;
    private final RepositoryCommentaire repositoryCommentaire;
    private final RepositorySolution repositorySolution;
  //  private final RepositoryEtat repositoryEtat;

    @Override
    public Probleme creerProbleme(Probleme probleme) {
        return repositoryProbleme.save(probleme);
    }

    @Override
    public Solution creerSolution(Solution solution) {
        return repositorySolution.save(solution);
    }

    @Override
    public Commentaire creerCommentaire(Commentaire commentaire) {
        return repositoryCommentaire.save(commentaire);
    }

    /*@Override
    public Etat creerEtat(Etat etat) {
        return repositoryEtat.save(etat);
    }*/
}
