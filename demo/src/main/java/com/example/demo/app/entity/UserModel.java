package com.example.demo.app.entity;

import com.example.demo.app.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = UserRepository.TABLE)
public class UserModel {
    @Id
    @Column(nullable = false)
    private String userId;

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    @JsonIgnore
    private String password;
    private Boolean status;
    @OneToOne
    private RoleModel role;

    @OneToOne
    private PositionModel position;
}
