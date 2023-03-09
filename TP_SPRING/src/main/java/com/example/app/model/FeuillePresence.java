package com.example.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class FeuillePresence {

    @Id
    @GeneratedValue
    private long id;
    private int annee;
    private int mois;

    @OneToMany
    private List<LignePresence> lignesPresences;

    @ManyToOne
    private Etudiant etudiant;

    public FeuillePresence() {
    }

    public FeuillePresence(int annee, int mois) {
        this.annee = annee;
        this.mois = mois;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAnnee() {
        return this.annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return this.mois;
    }

    public void setMonth(int m) {
        this.mois = m;
    }

    public List<LignePresence> getLignesPresences() {
        return this.lignesPresences;
    }

    public void addLignePresence(LignePresence lp) {
        this.lignesPresences.add(lp);
    }

    public void removeLignePresence(LignePresence lp) {
        this.lignesPresences.remove(lp);
    }

    public Etudiant getEtudiant() {
        return this.etudiant;
    }

    public void setEtudiant(Etudiant e) {
        this.etudiant = e;
    }

}
