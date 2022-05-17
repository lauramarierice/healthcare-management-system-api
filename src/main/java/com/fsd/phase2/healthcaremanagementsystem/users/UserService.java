package com.fsd.phase2.healthcaremanagementsystem.users;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
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

    public ResponseEntity<?> login(String userName, String password) {
        UserDTO userDTO = userMapper.map(userRepository.findByUserNameAndPassword(userName, password)
                .orElse(null));

        if(userDTO == null) {
           return new ResponseEntity<String>("Username or Password is incorrect!", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public UserDTO getUserInformationByUserId(Long id) {
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!")));
    }

    public ResponseEntity<?> registerNewUser(UserDTO userDTO) {

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
        newUser.setAddress(userDTO.getAddress());
        newUser.setPhoneNumber(userDTO.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        RoleEntity roles = roleService.findRolesByName("ROLE_USER");
        newUser.setRoles(Collections.singleton(roles));

        userRepository.save(newUser);

        UserDTO newlyRegisteredUser = userMapper.map(userRepository.findByUserId(newUser.getUserId()).orElse(null));

        return new ResponseEntity<UserDTO>(newlyRegisteredUser, HttpStatus.OK);
    }

    public UserDTO modifyUserInfo(UserDTO userDTO, Long userId) {
        return userRepository.findByUserId(userId)
                .map(patient -> {
                    patient.setEmail(userDTO.getEmail());
                    patient.setPassword(userDTO.getPassword());
                    patient.setPhoneNumber(userDTO.getPhoneNumber());
                    patient.setAddress(userDTO.getAddress());
                    userRepository.save(patient);
                    return userMapper.map(patient);
                })
                .orElse(userDTO);
    }
}