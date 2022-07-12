package com.example.demo.app.controller;

import com.example.demo.app.dto.request.role.RoleRequest;
import com.example.demo.app.service.RoleService;
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
class RoleControllerTest {

    @InjectMocks
    RoleController roleController;

    @Mock
    RoleService roleService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(roleController, "validator", Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    void listRoleTestSuccess(){
        var response = roleController.listRole();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void createRoleTestSuccess(){
        var request = RoleRequest.builder()
                .roleId("")
                .roleName("mock")
                .build();
        var response = roleController.createRole(request);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void updateRoleTestSuccess(){
        var request = RoleRequest.builder()
                .roleId("mock")
                .roleName("mock")
                .build();
        var response = roleController.updateRole(request);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
