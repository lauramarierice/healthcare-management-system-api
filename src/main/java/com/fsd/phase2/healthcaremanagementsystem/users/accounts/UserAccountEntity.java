package com.fsd.phase2.healthcaremanagementsystem.users.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString
public class UserAccountEntity {

    @EmbeddedId
    private UserAccountId id;

    @Column(name = "ACCOUNT_NUMBER", insertable = false, updatable = false)
    private Long accountNumber;

    @Column(name = "USER_ID", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "BALANCE")
    private double balance;
}
