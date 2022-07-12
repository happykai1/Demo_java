package com.example.demo.app.service;

import com.example.demo.app.dto.request.role.RoleRequest;
import com.example.demo.app.entity.RoleModel;
import com.example.demo.app.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demo.core.ResponseStatus.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class RoleServiceTest {
    @InjectMocks
    RoleService roleService;

    @Mock
    RoleRepository roleRepository;

    @Test
    void createRoleTestSuccess(){
        var request = RoleRequest.builder()
                .roleId("")
                .roleName("mock")
                .build();

        var response = roleService.saveRole(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }

    @Test
    void updateRoleTestSuccess(){
        var request = RoleRequest.builder()
                .roleId("mock")
                .roleName("mock")
                .build();

        var role = RoleModel.builder()
                .roleId("mock")
                .roleName("mock")
                .build();

        when(roleRepository.findByRoleId(anyString())).thenReturn(role);
        var response = roleService.saveRole(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }
}
