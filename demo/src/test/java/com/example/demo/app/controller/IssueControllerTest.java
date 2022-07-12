package com.example.demo.app.controller;

import com.example.demo.app.dto.request.issue.IssueRequest;
import com.example.demo.app.service.IssueService;
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
class IssueControllerTest {

    @InjectMocks
    IssueController issueController;

    @Mock
    IssueService issueService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(issueController, "validator", Validation.buildDefaultValidatorFactory().getValidator());
    }

    @Test
    void listIssueTestSuccess(){
        var response = issueController.listIssue();
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void createIssueTestSuccess(){
        var request = IssueRequest.builder()
                .issueId("")
                .title("mock")
                .createBy("mock")
                .updateBy("mock")
                .build();
        var response = issueController.createIssue(request);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void updateIssueTestSuccess(){
        var request = IssueRequest.builder()
                .issueId("mock")
                .title("mock")
                .createBy("mock")
                .updateBy("mock")
                .build();
        var response = issueController.updateIssue(request);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void deleteIssueTestSuccess(){
        var response = issueController.deleteIssue("mock");
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
