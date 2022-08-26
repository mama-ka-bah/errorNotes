package com.error.errorNotes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "compte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {

    @Id
    private String username;

    private String password;
    private String role;
}
