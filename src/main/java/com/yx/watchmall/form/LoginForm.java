package com.yx.watchmall.form;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {
    @NotBlank
    String email;
    @NotBlank
    String password;
}
