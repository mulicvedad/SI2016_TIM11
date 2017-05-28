package com.timxyz.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
public class Item extends BaseModel {
    private String skuNumber;
    private String name;
    private String unitOfMeasurement;
    private String purchasedBy;
    private String personResponsible;
    private Date dateOfPurchase;
    private BigDecimal value;
    private Collection<AuditItem> auditItems;
    private Category category;
    private Location location;
    private Collection<PastAuditItem> pastAuditItems;

    public Item() {
    }

    public Item (String skuNumber, String name, String unitOfMeasurement, String purchasedBy, String personResponsible, Date dateOfPurchase, BigDecimal value, Collection<AuditItem> auditItems, Category category, Location location, Collection<PastAuditItem> pastAuditItems) {
        this.skuNumber = skuNumber;
        this.name = name;
        this.unitOfMeasurement = unitOfMeasurement;
        this.purchasedBy = purchasedBy;
        this.personResponsible = personResponsible;
        this.dateOfPurchase = dateOfPurchase;
        this.value = value;
        this.auditItems = auditItems;
        this.category = category;
        this.location = location;
        this.pastAuditItems = pastAuditItems;
    }

    @Basic
    @Column(name = "skuNumber", nullable = false)
    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    @Basic
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "unitOfMeasurement", nullable = false)
    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    @Basic
    @Column(name = "purchasedBy", nullable = false)
    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    @Basic
    @Column(name = "personResponsible", nullable = false)
    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }

    @Basic
    @Column(name = "dateOfPurchase", nullable = false)
    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Basic
    @Column(name = "value", nullable = false)
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "item")
    @JsonIgnore
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
    @JsonIgnore
    public Collection<PastAuditItem> getPastAuditItems() {
        return pastAuditItems;
    }

    public void setPastAuditItems(Collection<PastAuditItem> pastAuditItems) {
        this.pastAuditItems = pastAuditItems;
    }
}
