package com.error.errorNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "technologie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Technologie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;


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
            joinColumns = @JoinColumn(name = "probleme_id"),
            inverseJoinColumns = @JoinColumn(name = "techno_id")
    )
    private List<Probleme> problemes = new ArrayList<>();


}
