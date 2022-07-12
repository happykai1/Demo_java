package com.example.demo.app.dto.request.issue;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueRequest {

    @NotNull
    private String issueId;

    private String title;
    @JsonProperty("create_by")
    private String createBy;
    @JsonProperty("update_by")
    private String updateBy;

}
