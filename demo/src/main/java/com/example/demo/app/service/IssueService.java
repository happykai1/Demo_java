package com.example.demo.app.service;

import com.example.demo.app.dto.request.issue.IssueRequest;
import com.example.demo.app.dto.response.issue.IssueResponse;
import com.example.demo.app.entity.IssueModel;
import com.example.demo.app.exception.DemoException;
import com.example.demo.app.repository.IssueRepository;
import com.example.demo.core.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.core.ResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssueService {

    final IssueRepository issueRepository;

    public ResponseBody<Object> listIssue() {
        var issues = issueRepository.findAll().stream()
                .map(issue -> IssueResponse.builder()
                        .issueId(issue.getIssueId())
                        .title(issue.getTitle())
                        .createBy(issue.getCreateBy())
                        .createDate(issue.getCreateDate())
                        .updateDate(issue.getUpdateDate())
                        .build())
                .collect(Collectors.toList());

        var json = new ObjectMapper().createObjectNode();
        json.putPOJO("issue", issues);

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, json);
        return response;
    }

    @Transactional
    public ResponseBody<Object> saveIssue(IssueRequest request) {

        if (request.getIssueId() == null || request.getIssueId().isEmpty()){
            var issue = IssueModel.builder()
                    .issueId(UUID.randomUUID().toString().replace("-", ""))
                    .title(request.getTitle())
                    .createBy(request.getCreateBy())
                    .updateBy(request.getUpdateBy())
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();
            issueRepository.save(issue);

        }else{
            var issue = issueRepository.findByIssueId(request.getIssueId());
            issue.setTitle(request.getTitle());
            issue.setCreateBy(request.getCreateBy());
            issue.setUpdateBy(request.getUpdateBy());
            issue.setCreateDate(LocalDateTime.now());
            issue.setUpdateDate(LocalDateTime.now());
            issueRepository.save(issue);
        }

        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, "");
        return response;
    }

    @Transactional
    public ResponseBody<Object> deleteIssue(String issueId) {

        var issue = issueRepository.findByIssueId(issueId);
        if (issue == null) {
            throw new DemoException(HttpStatus.OK, INVALID_ISSUE_ID);
        }

        issueRepository.deleteById(issueId);
        var response = new ResponseBody<>();
        response.setOperationSuccess(SUCCESS, "");
        return response;
    }

}
