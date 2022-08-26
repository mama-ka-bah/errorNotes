package com.error.errorNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "solution")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;
    private Date date;

    @OneToOne
    @JoinColumn(name = "probleme_id")
    private Probleme probleme;

}
