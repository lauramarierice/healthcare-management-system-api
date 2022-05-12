package com.fsd.phase2.healthcaremanagementsystem.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users/login", params = {"username", "password"})
    public ResponseEntity<?> login(@RequestParam("username") String userName, @RequestParam("password") String password) {
        return userService.login(userName, password);
    }

    @GetMapping(value = "/users/{id}")
    public UserDTO getUserInformationByUserId(@PathVariable("id") Long id) {
        return userService.getUserInformationByUserId(id);
    }

    @PostMapping(value = "/users/register")
    public ResponseEntity<String> registerNewUser(@RequestBody UserDTO userDTO) {
        return userService.registerNewUser(userDTO);
    }

    @PutMapping(value = "/users/{id}")
    public UserDTO modifyUserInfo(@RequestBody UserDTO userDTO, @PathVariable("id") Long userId) {
        return userService.modifyUserInfo(userDTO, userId);
    }
}
