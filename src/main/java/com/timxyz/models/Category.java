package com.timxyz.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Category extends BaseModel {
    private String name;
    private Category parent;
    private Collection<Category> children;
    private Collection<Item> items;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent")
    public Collection<Category> getChildren() {
        return children;
    }

    public void setChildren(Collection<Category> children) {
        this.children = children;
    }

    @OneToMany(mappedBy = "category")
    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
