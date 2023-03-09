package com.example.app.service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;
import com.example.app.model.FeuillePresence;
import com.example.app.model.FeuillePresenceRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class FeuillePresenceService {

    @Autowired
    private FeuillePresenceRepository fpr;
    @Autowired
    private EtudiantRepository er;

    public void createFeuille(int annee, int mois, HttpSession session) {
        Etudiant owner = (Etudiant) session.getAttribute("etu");
        // check si la feuille n'existe pas
        if (fpr.findByAnneeAndMois(annee, mois).isEmpty()) {
            FeuillePresence fp = new FeuillePresence(annee, mois);
            fp.setEtudiant(owner);
            fpr.save(fp);
        }
    }

    public List<FeuillePresence> getAllFeuillesByUser(Etudiant etu) {
        return fpr.findByEtudiant(etu);
    }

    public void deleteFeuille(long id) {
        fpr.deleteById(id);
    }

    public Optional<FeuillePresence> getFeuillePresenceById(long id) {
        return fpr.findById(id);
    }

}
