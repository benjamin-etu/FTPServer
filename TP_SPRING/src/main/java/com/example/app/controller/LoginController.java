package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.service.EtudiantService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EtudiantService es;

    @RequestMapping("")
    public String loginPage() {
        return "login";
    }

    @PostMapping(path = "/log_user", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(String email, String mdp, HttpSession session) {
        boolean connected = es.login(mdp, email, session);
        return "redirect:/index";
    }

}
