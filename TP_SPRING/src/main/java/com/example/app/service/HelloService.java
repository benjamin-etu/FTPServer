package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class HelloService {

    @Autowired
    private EtudiantRepository er;

    public void index(Model model, HttpSession session) {
        Etudiant etu = (Etudiant) session.getAttribute("etu");
        if (etu != null) {
            String welcome = "<p>Bonjour " + etu.getPrenom() + "</p>";
            model.addAttribute("welcome_message", welcome);
            String linkToManager = "<li><a href='/manager'>Feuilles de presences</a></li>";
            model.addAttribute("link_if_user_connected", linkToManager);
        }
    }

}
