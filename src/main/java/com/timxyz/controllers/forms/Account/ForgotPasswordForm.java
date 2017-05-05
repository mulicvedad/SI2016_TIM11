package com.timxyz.controllers.forms.Account;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ForgotPasswordForm {
    @Size(min = 8, max = 255) @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
