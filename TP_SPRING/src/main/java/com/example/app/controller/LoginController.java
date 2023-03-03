package com.example.app.controller;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

import ch.qos.logback.core.status.Status;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EtudiantRepository er;

    @RequestMapping("")
    public String loginPage() {
        return "login";
    }

    @PostMapping(path = "/log_user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(String email, String mdp, HttpSession session) {
        String status_message;
        // on doit vérifier que le couple mdp/mail correspond à une entrée
        // dans la base
        // avant tout je vérifie si il y a un et un seul compte pour cet email
        if (er.findByEmail(email).size() == 1) {
            // je recup l'étudiant
            Etudiant etu = er.findByEmail(email).get(0);

            // je vérifie le mdp hash au mdp de etu
            if (etu.getMdp().equals(mdp)) {
                // je met l'étudiant dans la session et le déclare
                // comme "online"
                etu.setOnline(true);
                er.save(etu);
                session.setAttribute("etu", etu);

            }
            status_message = "Tu es bien connecté, bravo";

        } else {
            status_message = "Mauvaise combinaison email, mot de passe.";
        }
        // TODO: afficher le message d'erreur
        return "redirect:/index";
    }

}
