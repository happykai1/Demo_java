package com.example.demo.app.entity;

import com.example.demo.app.repository.RoleRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = RoleRepository.TABLE)
public class RoleModel {

    @Id
    @Column(name="ROLE_ID",nullable = false)
    private String roleId;

    @Column(name="ROLE_NAME")
    private String roleName;
}
