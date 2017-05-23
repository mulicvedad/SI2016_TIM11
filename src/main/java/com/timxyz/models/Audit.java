package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Audit extends BaseModel {
    private String name;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Byte finished;
    private Account account;
    private Location location;
    private Collection<AuditItem> auditItems;
    private Collection<PastAudit> pastAudits;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "createdAt")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updatedAt")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "finished")
    public Byte getFinished() {
        return finished;
    }

    public void setFinished(Byte finished) {
        this.finished = finished;
    }
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id")
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "audit")
    public Collection<AuditItem> getAuditItems() {
        return auditItems;
    }

    public void setAuditItems(Collection<AuditItem> auditItems) {
        this.auditItems = auditItems;
    }

    @OneToMany(mappedBy = "audit")
    public Collection<PastAudit> getPastAudits() {
        return pastAudits;
    }

    public void setPastAudits(Collection<PastAudit> pastAudits) {
        this.pastAudits = pastAudits;
    }
}
