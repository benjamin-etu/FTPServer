package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.service.EtudiantService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @Autowired
    private EtudiantService es;

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        es.logout(session);
        return "redirect:/index";
    }
}
