package com.error.errorNotes.repository;

import com.error.errorNotes.model.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEtat extends JpaRepository<Etat, Long> {

    Etat findByNom(String nom);
}
