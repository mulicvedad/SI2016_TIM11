package com.timxyz.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Item extends BaseModel {
    private String skuNumber;
    private String name;
    private String unitOfMeasurement;
    private String purchasedBy;
    private String personResponsible;
    private Timestamp dateOfPurchase;
    private BigDecimal value;
    private Collection<AuditItem> auditItems;
    private Category category;
    private Location location;
    private Collection<PastAuditItem> pastAuditItems;

    @Basic
    @Column(name = "skuNumber")
    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unitOfMeasurement")
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Basic
    @Column(name = "purchasedBy")
    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    @Basic
    @Column(name = "personResponsible")
    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }

    @Basic
    @Column(name = "dateOfPurchase")
    public Timestamp getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Timestamp dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Basic
    @Column(name = "value")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "item")
    public Collection<AuditItem> getAuditItems() {
        return auditItems;
    }

    public void setAuditItems(Collection<AuditItem> auditItems) {
        this.auditItems = auditItems;
    }

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne
    @JoinColumn(name = "locationId", referencedColumnName = "id", nullable = false)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "item")
    public Collection<PastAuditItem> getPastAuditItems() {
        return pastAuditItems;
    }

    public void setPastAuditItems(Collection<PastAuditItem> pastAuditItems) {
        this.pastAuditItems = pastAuditItems;
    }
}
