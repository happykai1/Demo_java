package com.example.demo.app.controller;

import com.example.demo.app.dto.request.role.RoleRequest;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    final RoleService roleService;
    final Validator validator;

    @GetMapping("/list")
    public ResponseEntity<Object> listRole() {
        return ResponseEntity.ok(roleService.listRole());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createRole(@RequestBody RoleRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(roleService.saveRole(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateRole(@RequestBody RoleRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(roleService.saveRole(request));
    }

    private <T> void validateRequest(T request) {
        var violations = validator.validate(request);
        if (!violations.isEmpty()) throw new DemoException(violations);
    }
}
