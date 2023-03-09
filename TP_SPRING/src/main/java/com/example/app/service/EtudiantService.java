package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository er;

    public boolean login(String mdp, String email, HttpSession session) {
        // on doit vérifier que le couple mdp/mail correspond à une entrée
        // dans la base
        // avant tout je vérifie si il y a un et un seul compte pour cet email
        if (er.findByEmail(email).size() == 1) {
            // je recup l'étudiant
            Etudiant etu = er.findByEmail(email).get(0);
            // je vérifie le mdp
            if (etu.getMdp().equals(mdp)) {
                // je met l'étudiant dans la session et le déclare
                // comme "online"
                etu.setOnline(true);
                er.save(etu);
                session.setAttribute("etu", etu);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean register(String email, String nom, String prenom, String mdp) {
        // on test que l'email renseigné par l'étudiant n'existe pas déjà
        if (er.findByEmail(email).isEmpty()) {
            // TODO: hash du mdp

            // on crée l'étudiant
            Etudiant etu = new Etudiant(nom, prenom, email, mdp);
            // enregistrement
            er.save(etu);
            return true;
        } else {
            return false;
        }
    }

    public void logout(HttpSession session) {
        Etudiant etu = (Etudiant) session.getAttribute("etu");
        if (etu != null) {
            etu.setOnline(false);
            session.removeAttribute("etu");
            er.save(etu);
        }
    }

    public boolean isConnected(HttpSession session) {
        if (session.getAttribute("etu") != null) {
            return true;
        }
        return false;
    }

}
