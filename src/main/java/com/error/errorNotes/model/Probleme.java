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

<<<<<<< HEAD
    @ManyToMany
=======
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
//definition de la relation entre les deux tables Jointable pour lier les deux colone dans chaque table
    @JoinTable(
            name = "probleme_technologies",
            joinColumns = @JoinColumn(name = "techno_id"),
            inverseJoinColumns = @JoinColumn(name = "probleme_id")
    )
>>>>>>> authentification
    private List<Technologie> technologies = new ArrayList<>();


}
