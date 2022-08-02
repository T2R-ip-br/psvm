package com.sukhoev.psms.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    @RequestMapping
    public String activator() {
        return "регистрация";
    }
}
