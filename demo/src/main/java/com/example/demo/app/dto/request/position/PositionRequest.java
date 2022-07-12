package com.example.demo.app.dto.request.position;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionRequest {

    @JsonProperty("position_id")
    private String positionId;

    @NotBlank
    @JsonProperty("position_name")
    private String positionName;
}
