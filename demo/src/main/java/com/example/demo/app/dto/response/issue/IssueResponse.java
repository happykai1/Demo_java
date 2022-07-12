package com.example.demo.app.dto.response.issue;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IssueResponse {

    private String issueId;
    private String title;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String createBy;
}
