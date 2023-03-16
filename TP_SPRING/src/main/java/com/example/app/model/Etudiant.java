package com.example.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Etudiant {
    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String nom;
    private String prenom;
    private String mdp;
    private boolean online;

    @OneToMany(mappedBy = "etudiant", fetch = FetchType.EAGER)
    private List<FeuillePresence> feuillesPresences;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String email, String mdp) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.mdp = mdp;
        this.online = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String p) {
        this.prenom = p;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String p) {
        this.email = p;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setMdp(String p) {
        this.mdp = p;
    }

    public boolean getOnline() {
        return this.online;
    }

    public void setOnline(boolean p) {
        this.online = p;
    }

    public List<FeuillePresence> getFeuillesPresences() {
        return this.feuillesPresences;
    }

    public void addFeuillePresence(FeuillePresence fp) {
        this.feuillesPresences.add(fp);
    }

    public void removeFeuillePresence(FeuillePresence fp) {
        this.feuillesPresences.remove(fp);
    }

}
