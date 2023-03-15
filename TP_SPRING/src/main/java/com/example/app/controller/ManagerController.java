package com.example.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.model.Etudiant;
import com.example.app.model.FeuillePresence;
import com.example.app.service.EtudiantService;
import com.example.app.service.FeuillePresenceService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private EtudiantService es;

    @Autowired
    private FeuillePresenceService fps;

    @RequestMapping("")
    public String index(HttpSession session, Model model, HttpServletRequest request) {
        if (es.isConnected(session)) {
            Etudiant etu = (Etudiant) session.getAttribute("etu");
            model.addAttribute("feuilles", fps.getAllFeuillesByUser(etu));
            return "manager";
        }
        return "redirect:/index";
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createFeuille(int annee, int mois, HttpSession session) {
        if (es.isConnected(session)) {
            fps.createFeuille(annee, mois, session);
            return "redirect:/manager";
        }
        return "redirect:/index";
    }

    @PostMapping(path = "/feuille/create/ligne/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createLigne(@PathVariable long id, int jour, String hd, String hf, 
    String subject, String teacher, HttpSession session){
        if (es.isConnected(session)){
            fps.createLigne(id, jour, hd, hf, subject, teacher);
            String returnUrl = "redirect:/manager/feuille/"+Long.toString(id);
            return returnUrl;
        }
        return "redirect:/index";
    }

    @GetMapping("/feuille/{id}")
    public String showFeuille(@PathVariable long id, HttpSession session, Model model, HttpServletRequest request) {
        if (es.isConnected(session)) {
            FeuillePresence fp = fps.getFeuillePresenceById(id);
            if (fp != null) {
                model.addAttribute("feuille", fp);
                return "feuille";
            }
            return "redirect:/index";
        }
        return "redirect:/index";
    }

    @GetMapping("/delete/feuille/{id}")
    public String deleteFeuille(@PathVariable long id, HttpSession session, Model model) {
        if (es.isConnected(session)) {
            fps.deleteFeuille(id);
            return "redirect:/manager";
        }
        return "redirect:/index";
    }

    @GetMapping("/delete/ligne/{id}")
    public String deleteLigne(@PathVariable long id, HttpSession session, Model model, @RequestParam long feuilleId) {
        if (es.isConnected(session)) {
            fps.deleteLigne(id);
            return "redirect:/manager/feuille/"+Long.toString(feuilleId);
        }
        return "redirect:/index";
    }

}
