package com.timxyz.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Status extends BaseModel {
    private String name;
    private Collection<AuditItem> auditItems;
    private Collection<PastAuditItem> pastAuditItems;

    public Status(){

    }

    public Status(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "status")
    public Collection<AuditItem> getAuditItems() {
        return auditItems;
    }

    public void setAuditItems(Collection<AuditItem> auditItems) {
        this.auditItems = auditItems;
    }

    @OneToMany(mappedBy = "status")
    public Collection<PastAuditItem> getPastAuditItems() {
        return pastAuditItems;
    }

    public void setPastAuditItems(Collection<PastAuditItem> pastAuditItems) {
        this.pastAuditItems = pastAuditItems;
    }
}
