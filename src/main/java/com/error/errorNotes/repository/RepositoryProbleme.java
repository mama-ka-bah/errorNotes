package com.error.errorNotes.repository;

import com.error.errorNotes.model.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryProbleme extends JpaRepository<Probleme, Long> {

    @Query(value = "SELECT Probleme.titre, Probleme.descpt, Probleme.date, Etat.nom, Technologie.nom FROM Probleme, Etat where etat_id = id",nativeQuery = true)
    public Iterable<Object[]> FIND_PROBLEME();

    Probleme findByTitre(String titre);
}
