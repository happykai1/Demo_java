package com.example.demo.app.controller;

import com.example.demo.app.dto.request.issue.IssueRequest;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validator;

@RestController
@RequestMapping("/issue")
@RequiredArgsConstructor
public class IssueController {

    final IssueService issueService;
    final Validator validator;

    @GetMapping("/list")
    public ResponseEntity<Object> listIssue() {
        return ResponseEntity.ok(issueService.listIssue());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createIssue(@RequestBody IssueRequest request) {
        return ResponseEntity.ok(issueService.saveIssue(request));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateIssue(@RequestBody IssueRequest request) {
        return ResponseEntity.ok(issueService.saveIssue(request));
    }

    @DeleteMapping("/delete/{issueId}")
    public ResponseEntity<Object> deleteIssue(@PathVariable("issueId") String issueId){
        return ResponseEntity.ok(issueService.deleteIssue(issueId));
    }

    private <T> void validateRequest(T request) {
        var violations = validator.validate(request);
        if (!violations.isEmpty()) throw new DemoException(violations);
    }
}