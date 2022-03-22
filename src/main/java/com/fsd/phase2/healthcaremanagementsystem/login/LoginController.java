package com.fsd.phase2.healthcaremanagementsystem.login;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}
