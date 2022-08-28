package com.error.errorNotes.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Probleme_technologies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "probleme_id")
    private Probleme probleme;

    @ManyToOne
    @JoinColumn(name = "techno_id")
    private Technologie techno;
}
