package com.error.errorNotes.repository;

import com.error.errorNotes.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUtilisateur extends JpaRepository<Utilisateur, Long> {

}
