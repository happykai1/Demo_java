package com.example.demo.app.controller;

import com.example.demo.app.dto.request.position.PositionRequest;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Validator;

@RequiredArgsConstructor
@RestController
@RequestMapping("/position")
public class PositionController {

    final PositionService positionService;
    final Validator validator;

    @GetMapping("/list")
    public ResponseEntity<Object> listPosition() {
        return ResponseEntity.ok(positionService.listPosition());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPosition(@RequestBody PositionRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(positionService.savePosition(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updatePosition(@RequestBody PositionRequest request) {
        this.validateRequest(request);
        return ResponseEntity.ok(positionService.savePosition(request));
    }

    private <T> void validateRequest(T request) {
        var violations = validator.validate(request);
        if (!violations.isEmpty()) throw new DemoException(violations);
    }
}
