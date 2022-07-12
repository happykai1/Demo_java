package com.example.demo.app.service;

import com.example.demo.app.dto.request.role.RoleRequest;
import com.example.demo.app.dto.response.role.RoleResponse;
import com.example.demo.app.entity.RoleModel;
import com.example.demo.app.repository.RoleRepository;
import com.example.demo.core.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.core.ResponseStatus.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleService {

    final RoleRepository roleRepository;

    public ResponseBody<Object> listRole() {
        var roles = roleRepository.findAll().stream()
                .map(role -> RoleResponse.builder()
                        .roleId(role.getRoleId())
                        .roleName(role.getRoleName())
                        .build())
                .collect(Collectors.toList());

        var json = new ObjectMapper().createObjectNode();
        json.putPOJO("roles", roles);

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, json);
        return response;
    }

    @Transactional
    public ResponseBody<Object> saveRole(RoleRequest request){

        if (request.getRoleId() == null || request.getRoleId().isEmpty()){
            var role = RoleModel.builder()
                    .roleId(UUID.randomUUID().toString().replace("-", ""))
                    .roleName(request.getRoleName())
                    .build();
            roleRepository.save(role);

        }else{
            var role = roleRepository.findByRoleId(request.getRoleId());
            role.setRoleName(request.getRoleName());
            roleRepository.save(role);
        }

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, "");
        return response;
    }
}
