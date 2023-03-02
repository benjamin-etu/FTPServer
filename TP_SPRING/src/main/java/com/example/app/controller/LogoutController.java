package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @Autowired
    private EtudiantRepository er;

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        Etudiant etu = (Etudiant) session.getAttribute("etu");
        if (etu != null) {
            etu.setOnline(false);
            er.save(etu);
        }
        return "redirect:/index";
    }
}
