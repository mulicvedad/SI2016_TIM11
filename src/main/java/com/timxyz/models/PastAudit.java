package com.timxyz.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class PastAudit extends BaseModel {
    private Timestamp createdAt;
    private Audit audit;
    private Collection<PastAuditItem> pastAuditItems;

    @Basic
    @Column(name = "createdAt")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @ManyToOne
    @JoinColumn(name = "auditId", referencedColumnName = "id", nullable = false)
    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @OneToMany(mappedBy = "pastAudit")
    public Collection<PastAuditItem> getPastAuditItems() {
        return pastAuditItems;
    }

    public void setPastAuditItems(Collection<PastAuditItem> pastAuditItems) {
        this.pastAuditItems = pastAuditItems;
    }
}
