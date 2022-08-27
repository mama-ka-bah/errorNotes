package com.error.errorNotes.controller;

import com.error.errorNotes.model.Etat;
import com.error.errorNotes.model.Technologie;
import com.error.errorNotes.services.ServicesAdmins;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@AllArgsConstructor
public class AdminController {

   @Autowired
    final private ServicesAdmins servicesAdmins;

   @PostMapping("/createEtat")
   public Etat createEtat(@RequestBody Etat etat){
       return servicesAdmins.creerEtat(etat);
   }

    @PostMapping("/createTechnologie")
   public Technologie createTechnologie(@RequestBody Technologie technologie){
       return servicesAdmins.creerTechnologie(technologie);
   }

   @DeleteMapping("/DeleteCommentaire")
   public String deleteCommentaire(@PathVariable Long id){
       return servicesAdmins.supprimer(id);
   }
}
