package com.fsd.phase2.healthcaremanagementsystem.users;

import lombok.AllArgsConstructor;
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

    @GetMapping(value = "/users/{id}")
    public UserDTO getUserInformationByUserId(@PathVariable("id") Long id) {
        return userService.getUserInformationByUserId(id);
    }

    @GetMapping(value = "/users/{id}/login", params = {"password"})
    public UserDTO userLogin(@PathVariable("id") Long userId, @RequestParam("password") String password) {
        return userService.loginUser(userId, password);
    }

    @PostMapping(value = "/users/register")
    public UserDTO registerNewUser(@RequestBody UserEntity userEntity) {
        return userService.registerNewUser(userEntity);
    }

    @PutMapping(value = "/users/{id}")
    public UserDTO modifyUserInfo(@RequestBody UserEntity userEntity, Long id) {
        return userService.modifyUserInfo(userEntity, id);
    }
}
