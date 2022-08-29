package com.error.errorNotes.repository;

import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Probleme_technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryProblemeTechnologie extends JpaRepository<Probleme_technologies, Long> {
    Probleme_technologies findByProblemet(Probleme problemet);

    @Query(value="select titre, descpt, date, t.nom from probleme p, etat e, technologie t, probleme_technologies pt where p.id=pt.probleme_id and  t.id=pt.techno_id and  p.etat_id=e.id and p.titre = :titre", nativeQuery = true)
    public Object FIND_PROBLEME_TECHNO_ETAT_PAR_TITRE(@Param("titre") String titre);
}
