package com.error.errorNotes.services;

import com.error.errorNotes.model.Compte;
import com.error.errorNotes.repository.RepositoryCompte;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ServicesUserImpl implements ServicesUsers{

    private final RepositoryCompte repositoryCompte;

    @Override
    public Boolean connexion(String email, String password) {
        Compte compte = repositoryCompte.findByEmail(email);

        if(compte != null && compte.getPassword().equals(password)){
            System.out.println("Connexion éffectuée avec succes");
            return true;
        }else {
            System.out.println("ce compte n'existe pas");
            return false;
        }
    }
}
