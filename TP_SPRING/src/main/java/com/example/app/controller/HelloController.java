package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HelloController {

    @Autowired
    private EtudiantRepository pr;

    @RequestMapping("/index")
    public String index(Model model, HttpSession session) {
        Etudiant etu = (Etudiant) session.getAttribute("etu");
        if (etu != null) {
            String welcome = "<p>Bonjour " + etu.getPrenom() + "</p>";
            model.addAttribute("welcome_message", welcome);
            String linkToManager = "<li><a href='/manager'>Feuilles de presences</a></li>";
            model.addAttribute("link_if_user_connected", linkToManager);
        }
        return "index";
    }

}
