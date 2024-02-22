package com.projects.echoLens.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projects.echoLens.enums.StatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseV1DTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Schema(
            description = "Response Status",
            required = true
    )
    private StatusEnum status;
    @Schema(
            description = "Code",
            required = false
    )
    private Integer code;
    @Schema(
            description = "Message",
            required = true
    )
    private String message;

    public ResponseV1DTO() {
    }

    public StatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

