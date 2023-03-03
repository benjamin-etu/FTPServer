package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private EtudiantRepository er;

    @RequestMapping("")
    private String register() {
        return "register";
    }

    @PostMapping(path = "/create_user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private String createUser(String email, String nom, String prenom, String mdp) {
        String status_message;
        // on test que l'email renseigné par l'étudiant n'existe pas déjà
        if (er.findByEmail(email).isEmpty()) {
            // TODO: hash du mdp

            // on crée l'étudiant
            Etudiant etu = new Etudiant(nom, prenom, email, mdp);
            // enregistrement
            er.save(etu);
            status_message = "Compte étudiant crée avec succès.";
        } else {
            status_message = "Il y a déjà un compte etudiant existant pour cet email. Réessaye avec un autre.";
        }
        // TODO: RETOURNER UN MESSAGE SUCCES OU ERREUR DANS L'INDEX
        return "redirect:/index";
    }

}
