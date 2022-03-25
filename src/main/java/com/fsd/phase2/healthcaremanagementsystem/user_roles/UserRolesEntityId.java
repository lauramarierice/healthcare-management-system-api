package com.fsd.phase2.healthcaremanagementsystem.user_roles;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class UserRolesEntityId implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ROLE_ID")
    private Integer roleId;
}
