package com.example.demo.app.service;

import com.example.demo.app.dto.request.user.UserRequest;
import com.example.demo.app.entity.PositionModel;
import com.example.demo.app.entity.RoleModel;
import com.example.demo.app.entity.UserModel;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.repository.PositionRepository;
import com.example.demo.app.repository.RoleRepository;
import com.example.demo.app.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demo.core.ResponseStatus.*;
import static com.example.demo.core.ResponseStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PositionRepository positionRepository;

    @Test
    void createUserTestSuccess(){
        var request = UserRequest.builder()
                .firstName("mock")
                .lastName("mock")
                .email("mock")
                .userName("mock")
                .password("mock")
                .status(true)
                .roleId("mock")
                .positionId("mock")
                .build();

        var role = RoleModel.builder()
                .roleId("mock")
                .roleName("mock")
                .build();

        var position = PositionModel.builder()
                .positionId("mock")
                .positionName("mock")
                .build();

        when(roleRepository.findByRoleId(anyString())).thenReturn(role);
        when(positionRepository.findByPositionId(anyString())).thenReturn(position);
        var response = userService.saveUser(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }

    @Test
    void updateUserTestSuccess(){
        var request = UserRequest.builder()
                .userId("mock")
                .firstName("mock")
                .lastName("mock")
                .email("mock")
                .userName("mock")
                .password("mock")
                .status(true)
                .roleId("mock")
                .positionId("mock")
                .build();

        var role = RoleModel.builder()
                .roleId("mock")
                .roleName("mock")
                .build();

        var position = PositionModel.builder()
                .positionId("mock")
                .positionName("mock")
                .build();

        var user = UserModel.builder()
                .userId("mock")
                .position(position)
                .role(role)
                .build();

        when(roleRepository.findByRoleId(anyString())).thenReturn(role);
        when(positionRepository.findByPositionId(anyString())).thenReturn(position);
        when(userRepository.findByUserId(anyString())).thenReturn(user);
        var response = userService.saveUser(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }

    @Test
    void checkRole() {
        var request = UserRequest.builder()
                .userId("mock")
                .firstName("mock")
                .lastName("mock")
                .email("mock")
                .userName("mock")
                .password("mock")
                .status(true)
                .roleId("mock")
                .positionId("mock")
                .build();

        var exception = assertThrows(DemoException.class, () -> userService.saveUser(request));
        assertEquals(INVALID_ROLE_ID.getCode(), exception.getErrorCode());
    }

    @Test
    void checkPosition() {
        var request = UserRequest.builder()
                .userId("mock")
                .firstName("mock")
                .lastName("mock")
                .email("mock")
                .userName("mock")
                .password("mock")
                .status(true)
                .roleId("mock")
                .positionId("mock")
                .build();

        var role = RoleModel.builder()
                .roleId("mock")
                .roleName("mock")
                .build();

        when(roleRepository.findByRoleId(anyString())).thenReturn(role);
        var exception = assertThrows(DemoException.class, () -> userService.saveUser(request));
        assertEquals(INVALID_POSITION_ID.getCode(), exception.getErrorCode());
    }


    @Test
    void deleteUserSuccess(){
        var response = userService.deleteUser("mock");
        assertEquals(SUCCESS, response.getResultMsg());
    }
}
