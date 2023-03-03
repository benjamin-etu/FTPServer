package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.model.EtudiantRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ManagerController {

    @Autowired
    private EtudiantRepository er;

    @RequestMapping("/manager")
    public String index(HttpSession session) {
        // on vérifie directement que l'étudiant est connecté
        // en vérifiant qu'il existe dans la variable de session
        if (session.getAttribute("etu") != null) {
            return "manager";
        } else {
            return "redirect:/index";
        }
    }

}
