package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class UserAccountDTO {

    private Long userId;

    private Long accountNumber;

    private Double balance;
}
