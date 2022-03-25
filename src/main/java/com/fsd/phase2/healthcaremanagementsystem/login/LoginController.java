package com.fsd.phase2.healthcaremanagementsystem.login;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> userLoginAuthentication(@RequestBody LoginDTO loginDTO) {
        return loginService.userLogin(loginDTO);
    }
}