package com.sabulous.todoapp.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Date deadline;

    private String createdBy;

    private Date creationDate;

    private Date completionDate;

    private Integer status;

    private boolean completed;

    @OneToMany
    private List<Item> todoItems = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Item> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<Item> todoItems) {
        this.todoItems = todoItems;
    }

    public void print() {
        System.out.print("id=");
        if(this.id != null) {
            System.out.print(this.id);
        } else {
            System.out.print("null");
        }

        System.out.print(", name=");
        if(this.name != null) {
            System.out.print(this.name);
        } else {
            System.out.print("null");
        }

        System.out.print(", name=");
        if(this.name != null) {
            System.out.print(this.name);
        } else {
            System.out.print("null");
        }

        System.out.print(", deadline=");
        if(this.deadline != null) {
            System.out.print(this.deadline);
        } else {
            System.out.print("null");
        }

        System.out.print(", createdBy=");
        if(this.createdBy != null) {
            System.out.print(this.createdBy);
        } else {
            System.out.print("null");
        }

        System.out.print(", creationDate=");
        if(this.creationDate != null) {
            System.out.print(this.creationDate.toString());
        } else {
            System.out.print("null");
        }

        System.out.print(", completionDate=");
        if(this.completionDate != null) {
            System.out.print(this.completionDate.toString());
        } else {
            System.out.print("null");
        }

        System.out.print(", status=");
        if(this.status != null) {
            System.out.print(this.status);
        } else {
            System.out.print("null");
        }
        
        System.out.print(", completed=");
        System.out.print(this.completed);

        System.out.println("---END");
    }

}