package com.sabulous.todoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.todoapp.model.Item;
import com.sabulous.todoapp.model.Todo;
import com.sabulous.todoapp.services.ItemService;
import com.sabulous.todoapp.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class ItemController {

    @Autowired
    private TodoService todoService;

    @GetMapping("{todoId}/items")
    public List<? extends Object> getItems(@PathVariable Integer todoId) {    
        Todo todo = todoService.getById(todoId);
        if(todo == null) {
            return new ArrayList<Item>();
        }

        return todo.getTodoItems();
    }

    @PostMapping("{todoId}/items")
    public Item addItem(@PathVariable Integer todoId, @RequestBody Item item) {
        Todo todo = todoService.getById(todoId);
        if(todo == null) {
            return null;
        }
        todo.getTodoItems().add(item);
        return item;
    }

    @DeleteMapping("{todoId}/items/{itemId}")
    public void deleteItem(@PathVariable Integer todoId, @PathVariable Integer itemId) {
        Todo todo = todoService.getById(todoId);
        if(todo == null) {
            throw new NullPointerException("Delete item operation failed. Todo with " + todoId + " does not exist!");
        }
        todo.getTodoItems().removeIf(i -> i.getId() == itemId);
        return;
    }

}