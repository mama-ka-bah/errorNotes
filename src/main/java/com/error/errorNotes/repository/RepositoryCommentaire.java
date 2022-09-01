package com.error.errorNotes.repository;

import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RepositoryCommentaire extends JpaRepository<Commentaire, Long> {

     @Transactional
     Long deleteBySolution(Solution solution);

     //List<Commentaire> findAllBYSolution(Solution solution);

     @Query(value = "select * from commentaire where solution_id = :solution_id", nativeQuery = true)
     List<Commentaire> FIND_ALLA_COMMENTAIRE_PAR_SOLUION_ID(@Param("solution_id") Long solution_id);

}
