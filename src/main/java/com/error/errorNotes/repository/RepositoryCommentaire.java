package com.error.errorNotes.repository;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface RepositoryCommentaire extends JpaRepository<Commentaire, Long> {

     @Transactional
     Long deleteBySolution(Solution solution);
     List<Commentaire> findBYSolution(Solution solution);

}
