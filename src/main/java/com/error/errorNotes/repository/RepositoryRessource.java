package com.error.errorNotes.repository;

import com.error.errorNotes.model.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RepositoryRessource extends JpaRepository<Ressource, Long> {

    @Transactional
    @Modifying
    @Query(value="DELETE FROM ressource WHERE solution_id = :solution_id", nativeQuery = true)
    public void DELETE_RESSOURCE(@Param("solution_id") Long solution_id);
}
