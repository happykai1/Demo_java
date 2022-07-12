package com.example.demo.app.service;

import com.example.demo.app.dto.request.issue.IssueRequest;
import com.example.demo.app.entity.IssueModel;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demo.core.ResponseStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class IssueServiceTest {

    @InjectMocks
    IssueService issueService;

    @Mock
    IssueRepository issueRepository;

    @Test
    void createIssueTestSuccess(){
        var request = IssueRequest.builder()
                .issueId("")
                .title("mock")
                .createBy("mock")
                .updateBy("mock")
                .build();
        var response = issueService.saveIssue(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }

    @Test
    void updateIssueTestSuccess(){
        var request = IssueRequest.builder()
                .issueId("mock")
                .title("mock")
                .createBy("mock")
                .updateBy("mock")
                .build();

        var issue = IssueModel.builder()
                .issueId("mock")
                .build();

        when(issueRepository.findByIssueId(anyString())).thenReturn(issue);
        var response = issueService.saveIssue(request);
        assertEquals(SUCCESS, response.getResultMsg());
    }

    @Test
    void deleteIssueSuccess(){
        var issue = IssueModel.builder()
                .issueId("mock")
                .build();
        when(issueRepository.findByIssueId(anyString())).thenReturn(issue);
        var response = issueService.deleteIssue("mock");
        assertEquals(SUCCESS, response.getResultMsg());
    }

    @Test
    void deleteIssueNotSuccess(){
        when(issueRepository.findByIssueId(anyString())).thenReturn(null);
        var exception = assertThrows(DemoException.class, () -> issueService.deleteIssue("mock"));
        assertEquals(INVALID_ISSUE_ID.getCode(), exception.getErrorCode());
    }
}
