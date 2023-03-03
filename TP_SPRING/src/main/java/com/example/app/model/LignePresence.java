package com.example.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LignePresence {

    @Id
    @GeneratedValue
    private long id;
    private int dayOfMonth;

    @ManyToOne
    private FeuillePresence feuillePresence;

    public LignePresence() {
    }

    public LignePresence(byte dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDayOfMonth() {
        return this.dayOfMonth;
    }

    public void setDayOfMonth(int dm) {
        this.dayOfMonth = dm;
    }

    public FeuillePresence getFeuillePresence() {
        return this.feuillePresence;
    }

    public void setFeuillePresence(FeuillePresence fp) {
        this.feuillePresence = fp;
    }

}
