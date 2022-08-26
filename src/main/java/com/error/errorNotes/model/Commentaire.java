package com.error.errorNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commentaire")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "solution_id")
    private Solution solution;

    @ManyToOne
    @JoinColumn(name = "utilisateur")
    private Utilisateur utilisateur;

}
