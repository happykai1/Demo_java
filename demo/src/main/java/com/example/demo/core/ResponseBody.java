package com.example.demo.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody<T> {
    @JsonProperty("result_code")
    private int resultCode;

    @JsonProperty("result_msg")
    private String resultMsg;

    @JsonProperty("result_data")
    private T resultData;

    public void setOperationFail(String resultMsg, T errorData) {
        this.resultCode = 0;
        this.resultMsg = resultMsg;
        this.resultData = errorData;
    }

    public void setOperationSuccess(String resultMsg, T resultData) {
        this.resultCode = 1;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }
}
