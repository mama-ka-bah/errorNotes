package com.error.errorNotes.controller;

import com.error.errorNotes.model.*;
import com.error.errorNotes.services.ServicesUsers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;


    @Test
    void createProbleme() {

        Probleme probleme = new Probleme();

        String email = "amadou@gmail.com";
        String password = "diadje123@";
        String technos = "PHP Java";
        probleme.setTitre("Test 2");
        probleme.setDescpt("de ndhedn r dhjedje");

        userController.createProbleme(probleme, email, password, technos);

    }


    @Test
    void createSolution() {
        Solution solution = new Solution();

        String email = "amadou@gmail.com";
        String password = "diadje123@";
        String ressource = "www.google.com www.stackoverflow";
        String titre = "Test 28";
        solution.setContenu("solution dijejk edjde");


        userController.createSolution(solution,email,password,titre,ressource);
    }

    @Test
    void createCommentaire() {
        Commentaire commentaire = new Commentaire();

        String email = "amadou@gmail.com";
        String password = "diadje123@";
        String titre = "Test 28";
        commentaire.setContenu("commentaires 11111111111111");

        userController.createCommentaire(commentaire,email,password,titre);
    }

    @Test
    void updateSolution() {
        Solution solution = new Solution();

        String email = "amadou@gmail.com";
        String password = "diadje123@";
        String titre = "Test 28";
        solution.setContenu("ghjk ghjkl hjko hjkl jklm bkl");


        userController.updateSolution(solution,email,password,titre);

    }

    @Test
    void modifierProbleme() {
        Probleme probleme = new Probleme();
        Etat etat = new Etat();

        etat.setId(2L);
        String email = "amadou@gmail.com";
        String password = "diadje123@";
        String titre = "Test 28";
        probleme.setDescpt("description description description description description");
        probleme.setTitre("titre titre ");
        probleme.setEtat(etat);

        userController.modifierProbleme(probleme, email, password,titre);
    }

    @Test
    void mofifierCommentaire() {
        Commentaire commentaire = new Commentaire();

        String email = "amadou@gmail.com";
        String password = "diadje123@";
        Long id = 3L;

        commentaire.setContenu("nouveau commentaire");

        userController.mofifierCommentaire(commentaire,email,password,id);


    }
}