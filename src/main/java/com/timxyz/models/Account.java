package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Account extends BaseModel {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private Collection<AccessLog> accessLogs;
    private Role role;
    private Collection<Audit> audits;

    @Basic
    @Column(name = "fullName")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "email", unique=true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "username", unique=true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "account")
    public Collection<AccessLog> getAccessLogs() {
        return accessLogs;
    }

    public void setAccessLogs(Collection<AccessLog> accessLogs) {
        this.accessLogs = accessLogs;
    }

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    @JsonManagedReference
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "account")
    public Collection<Audit> getAudits() {
        return audits;
    }

    public void setAudits(Collection<Audit> audits) {
        this.audits = audits;
    }
}
