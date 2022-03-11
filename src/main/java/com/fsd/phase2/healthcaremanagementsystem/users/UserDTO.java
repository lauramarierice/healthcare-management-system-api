package com.fsd.phase2.healthcaremanagementsystem.users;

import com.fsd.phase2.healthcaremanagementsystem.orders.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class UserDTO {

    private Long userId;

    private Long userName;

    private String role;

    private String firstName;

    private String lastName;

    private String password;

    private Date birthDate;

    private String phoneNumber;

    private String email;

    private String address;

    private List<OrderDTO> orders;
}
