package com.fsd.phase2.healthcaremanagementsystem.users;

import com.fsd.phase2.healthcaremanagementsystem.users.roles.RoleEntity;
import com.fsd.phase2.healthcaremanagementsystem.users.roles.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    public ResponseEntity<?> getUserInformationByUserId(Long id) {
        UserDTO userDTO = userMapper.map(userRepository.findById(id).orElse(null));

        if (userDTO == null) {
            return new ResponseEntity<>("User not found!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity<String> registerNewUser(UserDTO userDTO) {

        // add check for username exists in a DB
        UserEntity userName = userRepository.findByUserName(userDTO.getUserName()).orElse(null);
        if (userName != null) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity emailExists = userRepository.findByEmail(userDTO.getEmail()).orElse(null);
        if (emailExists != null) {
            return new ResponseEntity<>("Email is already is use!", HttpStatus.BAD_REQUEST);
        }

        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setUserName(userDTO.getUserName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setBirthDate(userDTO.getBirthDate());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        RoleEntity roles = roleService.findRolesByName("ROLE_USER");
        newUser.setRoles(Collections.singleton(roles));

        userRepository.save(newUser);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    public UserDTO modifyUserInfo(UserDTO userDTO, Long id) {
        return userRepository.findById(id)
                .map(patient -> {
                    patient.setEmail(userDTO.getEmail());
                    patient.setPassword(userDTO.getPassword());
                    patient.setPhoneNumber(userDTO.getPhoneNumber());
                    patient.setAddress(userDTO.getAddress());
                    return userMapper.map(userRepository.save(userMapper.map(userDTO)));
                })
                .orElse(userDTO);
    }
}