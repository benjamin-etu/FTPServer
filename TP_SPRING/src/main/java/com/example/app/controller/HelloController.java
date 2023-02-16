package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.app.model.Etudiant;
import com.example.app.model.EtudiantRepository;

@Controller
public class HelloController {

    @Autowired
    private EtudiantRepository pr;

    @RequestMapping("/hello")
    public String saysHello(Model model){
        model.addAttribute("nom", "Marley");
        model.addAttribute("prenom", "Bob");
        model.addAttribute("image_url", "https://images.rtl.fr/~c/2000v2000/rtl/www/1330929-bob-marley-empereur-du-reggae-jamaicain-ici-en-1976.jpg");
        return "index";
    }

    @RequestMapping("/init")
    @ResponseBody
    public String init() {
        Etudiant p1 = new Etudiant("Marley", "Bob", "https://images.rtl.fr/~c/2000v2000/rtl/www/1330929-bob-marley-empereur-du-reggae-jamaicain-ici-en-1976.jpg");
        pr.save(p1);
        return "OK";
    }

    /*@RequestMapping("/all")
    @ResponseBody
    public String all() {
        String s = "";
        pr.findAll();
        for (Personne p: pr.findAll()) {
            s = s.concat(p.toString());
        }
        return s;
    }*/
    
}
