package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.service.HelloService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HelloController {

    @Autowired
    private HelloService hs;

    @RequestMapping("/index")
    public String index(Model model, HttpSession session) {
        hs.index(model, session);
        return "index";
    }

}
