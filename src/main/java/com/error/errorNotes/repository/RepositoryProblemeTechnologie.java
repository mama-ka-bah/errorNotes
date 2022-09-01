package com.error.errorNotes.repository;

import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Probleme_technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface RepositoryProblemeTechnologie extends JpaRepository<Probleme_technologies, Long> {
    Probleme_technologies findByProblemet(Probleme problemet);

    @Query(value="select titre, descpt, date, t.nom from probleme p, etat e, technologie t, probleme_technologies pt where p.id=pt.probleme_id and  t.id=pt.techno_id and  p.etat_id=e.id and p.titre = :titre", nativeQuery = true)
    public Object FIND_PROBLEME_TECHNO_ETAT_PAR_TITRE(@Param("titre") String titre);

    @Query(value="select titre, descpt, p.date, e.nom, s.contenu from probleme p, technologie t, probleme_technologies pt, solution s, etat e where p.id=pt.probleme_id and t.id=pt.techno_id and p.etat_id = e.id and s.probleme_id = p.id and p.titre = :titre", nativeQuery = true)
    public Object FIND_PROBLEME_TECHNO_ETAT_PAR_TITRE_SOLUTION(@Param("titre") String titre);


    @Transactional
    @Modifying
    @Query(value="DELETE FROM probleme_technologies WHERE probleme_id = :probleme_id", nativeQuery = true)
    public void DELETE_PROBLEME_TECHNOLOGIE_PAR_PROBLEME(@Param("probleme_id") Long probleme_id);
}
