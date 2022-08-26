package com.error.errorNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ressource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ressource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String lienDoc;

    @ManyToOne
    @JoinColumn(name = "solution_id")
    private Solution solution;
}
