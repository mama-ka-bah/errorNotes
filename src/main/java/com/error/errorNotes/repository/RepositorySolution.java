package com.error.errorNotes.repository;

import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorySolution extends JpaRepository<Solution, Long> {
    @Query(value = "select * from solution where probleme_id = :probleme_id", nativeQuery = true)
    public Solution FIND_SOLUTION_PAR_ID_PROBLEME(@Param("probleme_id") Long probleme_id);
}
