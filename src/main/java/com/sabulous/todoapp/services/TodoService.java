package com.sabulous.todoapp.services;

import java.util.List;

import com.sabulous.todoapp.model.Todo;

public interface TodoService extends CRUDService<Todo> {
    public List<Todo> listTodosByUsername(String username);
}