package com.example.app.model;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class LignePresence {

    @Id
    @GeneratedValue
    private long id;
    private int dayOfMonth;
    private String hStart;
    private String hEnd;
    private String subject;
    private String teacherName;

    @ManyToOne(fetch = FetchType.LAZY)
    private FeuillePresence feuillePresence;

    public LignePresence() {
    }

    public LignePresence(int dayOfMonth, String hS, String hEnd, String subject,
    String teacher) {
        this.dayOfMonth = dayOfMonth;
        this.hStart = hS;
        this.hEnd = hEnd;
        this.subject = subject;
        this.teacherName = teacher;

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

    public String getHStart() {
        return this.hStart;
    }

    public String getHEnd() {
        return this.hEnd;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getTeacherName() {
        return this.teacherName;
    }

    

}
