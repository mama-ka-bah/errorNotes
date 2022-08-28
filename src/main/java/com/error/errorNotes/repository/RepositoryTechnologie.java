package com.error.errorNotes.repository;

import com.error.errorNotes.model.Technologie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTechnologie extends JpaRepository<Technologie, Long> {
    Technologie findByNom(String nom);
}
