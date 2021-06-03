package com.yx.watchmall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterForm {
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phone;
}
