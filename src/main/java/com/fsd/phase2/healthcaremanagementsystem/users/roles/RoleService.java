package com.fsd.phase2.healthcaremanagementsystem.users.roles;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity findRolesByName(String name) {
        return roleRepository.findByRoleName(name).orElse(null);
    }
}