package com.fsd.phase2.healthcaremanagementsystem.users;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "authority", source = "authority")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "birthDate", source = "birthDate")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "address", source = "address")
    UserDTO map(UserEntity source);

    UserEntity map(UserDTO source);

    List<UserDTO> map(List<UserEntity> source);
}
