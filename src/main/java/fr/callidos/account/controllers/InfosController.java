package fr.callidos.account.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/")
public class InfosController {
    @GetMapping
    public String helloWorld() {
        return "H E L I X - AU TEMPS DONNE \n" +
                "VERSION 1.0.0";
    }
}
