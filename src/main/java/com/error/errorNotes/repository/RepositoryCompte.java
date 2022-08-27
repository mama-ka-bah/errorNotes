package com.error.errorNotes.repository;

import com.error.errorNotes.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCompte extends JpaRepository<Compte, Long> {
    Compte findByEmail(String email);
}
