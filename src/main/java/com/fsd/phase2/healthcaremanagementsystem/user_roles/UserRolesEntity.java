package com.fsd.phase2.healthcaremanagementsystem.user_roles;

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
@Table(name = "USER_ROLES")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@ToString
public class UserRolesEntity {

    @EmbeddedId
    private UserRolesEntityId id;

    @Column(name = "USER_ID", nullable = false, insertable = false, updatable = false)
    private Long userId;

    @Column(name = "ROLE_ID", nullable = false, insertable = false, updatable = false)
    private Integer roleId;

}
