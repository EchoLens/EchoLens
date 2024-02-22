package com.projects.echoLens.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequestV1DTO {
    @Schema(description = "Username of a customer", required = true)
    private String username;

    @Schema(description = "Password of a customer", required = true)
    private String password;

    @Schema(description = "email address of a customer", required = true)
    private String useremail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

}
