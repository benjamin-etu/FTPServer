package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

@Controller
public class HelloController {

    @Autowired
    private EtudiantRepository pr;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

}
