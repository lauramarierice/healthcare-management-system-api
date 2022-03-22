package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import com.fsd.phase2.healthcaremanagementsystem.users.UserDTO;
import com.fsd.phase2.healthcaremanagementsystem.users.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    UserAccountDTO map(UserAccountEntity source);

    UserAccountEntity map(UserAccountDTO source);

    List<UserAccountDTO> map(List<UserAccountEntity> source);
}
