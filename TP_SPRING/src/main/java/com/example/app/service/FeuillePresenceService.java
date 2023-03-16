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
import com.example.app.model.LignePresence;
import com.example.app.model.LignePresenceRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class FeuillePresenceService {

    @Autowired
    private FeuillePresenceRepository fpr;
    @Autowired
    private LignePresenceRepository lpr;
    @Autowired
    private EtudiantRepository er;

    public void createFeuille(int annee, int mois, HttpSession session) {
        Etudiant owner = (Etudiant) session.getAttribute("etu");
        // check si la feuille n'existe pas
        if (fpr.findByAnneeAndMoisAndEtudiant(annee, mois, owner).isEmpty()) {
            FeuillePresence fp = new FeuillePresence(annee, mois);
            fp.setEtudiant(owner);
            fpr.save(fp);
        }
    }

    public List<FeuillePresence> getAllFeuillesByUser(Etudiant etu) {
        return fpr.findByEtudiant(etu);
    }

    public void deleteFeuille(long id) {
        FeuillePresence fp = fpr.findById(id);
        if (fp != null) {
            for (LignePresence lp : fp.getLignesPresences()) {
                lpr.deleteById(lp.getId());
            }
        }
        fpr.deleteById(id);
    }

    public FeuillePresence getFeuillePresenceById(long id) {
        return fpr.findById(id);
    }

    public void createLigne(long id, int jour, String hd, String hf, String subject, String teacher) {
        // si la feuille existe
        FeuillePresence fp = fpr.findById(id);
        if (fp != null) {
            // on crée une ligne
            LignePresence lp = new LignePresence(
                    jour, hd, hf, subject, teacher);
            lp.setFeuillePresence(fp);
            lpr.save(lp);
            fp.addLignePresence(lp);
            fpr.save(fp);
        }

    }

    public void deleteLigne(long id) {
        lpr.deleteById(id);
    }

}
