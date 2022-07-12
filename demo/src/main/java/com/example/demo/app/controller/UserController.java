package com.example.demo.app.controller;

import com.example.demo.app.dto.request.user.UserRequest;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final Validator validator;

    @GetMapping("/list")
    public ResponseEntity<Object> listUser() {
        return ResponseEntity.ok(userService.listUser());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody UserRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody UserRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") String userId){
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    private <T> void validateRequest(T request) {
        var violations = validator.validate(request);
        if (!violations.isEmpty()) throw new DemoException(violations);
    }
}
