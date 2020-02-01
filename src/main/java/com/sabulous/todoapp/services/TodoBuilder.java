package com.sabulous.todoapp.services;

import java.sql.Date;

import com.sabulous.todoapp.model.Todo;

public class TodoBuilder {
    private Todo todo;

    public TodoBuilder() {
        todo = new Todo();
    }

    public Todo build() {
        return todo;
    }

    public TodoBuilder name(String name) {
        todo.setName(name);
        return this;
    }

    public TodoBuilder deadline(Date deadline) {
        todo.setDeadline(deadline);
        return this;
    }

    public TodoBuilder createdBy(String username) {
        todo.setCreatedBy(username);
        return this;
    }

    public TodoBuilder creationDate(Date creationDate) {
        todo.setCreationDate(creationDate);
        return this;
    }

    public TodoBuilder completionDate(Date completionDate) {
        todo.setCompletionDate(completionDate);
        return this;
    }

    public TodoBuilder status(Integer status) {
        todo.setStatus(status);
        return this;
    }

    public TodoBuilder completed(boolean completed) {
        todo.setCompleted(completed);
        return this;
    }
    
}