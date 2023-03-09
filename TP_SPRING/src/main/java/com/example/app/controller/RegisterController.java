package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;
import com.example.app.service.EtudiantService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private EtudiantService es;

    @RequestMapping("")
    private String register() {
        return "register";
    }

    @PostMapping(path = "/create_user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private String createUser(String email, String nom, String prenom, String mdp, HttpSession session) {
        boolean registered = es.register(email, nom, prenom, mdp);
        es.login(mdp, email, session);
        return "redirect:/index";
    }

}
