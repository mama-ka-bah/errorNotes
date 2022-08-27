package com.error.errorNotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "probleme")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Probleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String descpt;
    private Date date;


    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;


   @ManyToOne
   @JoinColumn(name = "etat_id")
    private Etat etat;

    @ManyToMany
    private List<Technologie> technologies = new ArrayList<>();


}
