package com.error.errorNotes.controller;


import com.error.errorNotes.model.Commentaire;
import com.error.errorNotes.model.Probleme;
import com.error.errorNotes.model.Solution;
import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesAdmins;
import com.error.errorNotes.services.ServicesUsers;
import com.error.errorNotes.services.ServicesVisitors;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    final private ServicesAdmins servicesAdmins;
    final private ServicesUsers servicesUsers;

    @PostMapping("/createProbleme")
    public Probleme createProbleme(@RequestBody Probleme probleme){
        return servicesUsers.creerProbleme(probleme);
    }

    @PostMapping("/createSolution")
    public Solution createSolution(@RequestBody Solution solution){
        return servicesUsers.creerSolution(solution);
    }

    @PostMapping("/createCommentaire")
    public Commentaire createCommentaire(@RequestBody Commentaire commentaire){
        return servicesUsers.creerCommentaire(commentaire);
    }

}
