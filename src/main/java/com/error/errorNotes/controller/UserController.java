package com.error.errorNotes.controller;


import com.error.errorNotes.model.Utilisateur;
import com.error.errorNotes.services.ServicesAdmins;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    final private ServicesAdmins servicesAdmins;

}
