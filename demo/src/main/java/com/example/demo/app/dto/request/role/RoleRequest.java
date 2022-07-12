package com.example.demo.app.dto.request.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {
    @JsonProperty("role_id")
    private String roleId;

    @NotNull
    @JsonProperty("role_name")
    private String roleName;
}
