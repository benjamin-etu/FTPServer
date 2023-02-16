package com.example.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Etudiant {
    private long id;
    private String email;
    private String nom;
    private String prenom;

    public Etudiant(String nom, String prenom, String email) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }

    @Id
    @GeneratedValue
    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom( String nom ) { this.nom = nom; }

    public String getPrenom() { return this.prenom; }
    public void setPrenom( String p ) { this.prenom = p; }

    public String getEmail() {return this.email;}
    public void setEmail(String p){this.email = p;}

}
