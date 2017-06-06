package com.timxyz.controllers.forms.Account;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by smusic on 5/25/17.
 */
public class AccountUpdateForm {

    @Size(min = 4, max = 255) @NotNull
    private String fullName;

    @Size(min = 3, max = 16) @NotNull
    private String username;

    @Email
    @Size(max = 255) @NotNull
    private String email;

    @Size(min = 8)
    private String password;

    @Min(1) @Max(3) @NotNull
    private Long roleId;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public boolean isPasswordUpdated() {
        return password != null && !password.isEmpty();
    }
}
