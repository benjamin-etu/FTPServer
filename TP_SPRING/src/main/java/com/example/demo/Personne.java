package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Personne {
    private long id;
    private String nom;
    private String prenom;
    private String image_url;

    public Personne(String nom, String prenom, String imageUrl) {
        this.nom = nom;
        this.prenom = prenom;
        this.image_url = imageUrl;
    }

    @Id
    @GeneratedValue
    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom( String nom ) { this.nom = nom; }
    public String getPrenom() { return this.prenom; }
    public void setPrenom( String p ) { this.prenom = p; }
    public String getImageUrl() { return this.image_url; }
    public void setImageUrl( String p ) { this.image_url = p; }
}
