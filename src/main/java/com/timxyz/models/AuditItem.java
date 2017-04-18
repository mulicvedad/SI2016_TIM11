package com.timxyz.models;

import javax.persistence.*;

@Entity
public class AuditItem extends BaseModel {
    private Boolean present;
    private Boolean skuCorrect;
    private String note;
    private Item item;
    private Audit audit;
    private Status status;

    @Basic
    @Column(name = "present")
    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    @Basic
    @Column(name = "skuCorrect")
    public Boolean getSkuCorrect() {
        return skuCorrect;
    }

    public void setSkuCorrect(Boolean skuCorrect) {
        this.skuCorrect = skuCorrect;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @ManyToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id", nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "auditId", referencedColumnName = "id", nullable = false)
    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @ManyToOne
    @JoinColumn(name = "statusId", referencedColumnName = "id", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
