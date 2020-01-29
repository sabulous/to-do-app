package com.sabulous.todoapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Integer id;

    private String content;

    private Integer status;

    @OneToMany
    private List<Item> dependentItems = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Item> getDependentItems() {
        return dependentItems;
    }

    public void setDependentItems(List<Item> dependentItems) {
        this.dependentItems = dependentItems;
    }
}