package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Embeddable
public class UserAccountId implements Serializable {

    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;

    @Column(name = "USER_ID")
    private Long userId;
}
