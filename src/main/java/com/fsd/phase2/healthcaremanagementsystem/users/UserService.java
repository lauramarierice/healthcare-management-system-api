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

    public UserDTO registerNewUser(UserEntity userEntity) {
        return userMapper.map(userRepository.save(userEntity));
    }

    public UserDTO modifyUserInfo(UserEntity userEntity, Long id) {
        return userRepository.findById(id)
                .map(patient -> {
                    patient.setEmail(userEntity.getEmail());
                    patient.setPassword(userEntity.getPassword());
                    patient.setPhoneNumber(userEntity.getPhoneNumber());
                    patient.setAddress(userEntity.getAddress());
                    return userMapper.map(userRepository.save(userEntity));
                })
                .orElse(userMapper.map(userEntity));
    }

    public UserDTO loginUser(Long userId, String password) {
        return userMapper.map(userRepository.findByUserIdAndPassword(userId, password)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("User/Password is incorrect");
                }));
    }

}