package com.example.demo.app.controller;

import com.example.demo.app.dto.request.user.UserRequest;
import com.example.demo.app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.Validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userController, "validator", Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    void listUserTestSuccess() {
        var response = userController.listUser();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createUserTestSuccess() {
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
        var response = userController.createUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void updateUserTestSuccess() {
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
        var response = userController.createUser(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteUserTestSuccess() {
        var response = userController.deleteUser("mock");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
