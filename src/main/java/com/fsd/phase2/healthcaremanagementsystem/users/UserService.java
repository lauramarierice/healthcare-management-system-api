package com.fsd.phase2.healthcaremanagementsystem.users;

import com.fsd.phase2.healthcaremanagementsystem.commons.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserDTO getUserInformationByUserId(Long id) {
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("User not found.");
                }));
    }

    public UserDTO registerNewUser(UserDTO userDTO) {
        return userMapper.map(userRepository.save(userMapper.map(userDTO)));
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

    public UserDTO loginUser(Long userId, String password) {
        return userMapper.map(userRepository.findByUserIdAndPassword(userId, password)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("User/Password is incorrect");
                }));
    }

}