package com.error.errorNotes.services;

import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.repository.RepositoryCommentaire;
import com.error.errorNotes.repository.RepositoryEtat;
import com.error.errorNotes.repository.RepositoryTechnologie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServicesAdminsImpl implements ServicesAdmins{

    private final RepositoryEtat repositoryEtat;
    private final RepositoryTechnologie repositoryTechnologie;
    private final RepositoryCommentaire repositoryCommentaire;

    @Override
    public Etat creerEtat(Etat etat) {
        return repositoryEtat.save(etat);
    }

    @Override
    public Technologie creerTechnologie(Technologie technologie) {
        return repositoryTechnologie.save(technologie);
    }

    @Override
    public String supprimer(long id) {
        repositoryCommentaire.deleteById(id);
        return "Commentaire supprimer";
    }

}
