package com.sabulous.todoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.todoapp.model.Item;
import com.sabulous.todoapp.model.Todo;
import com.sabulous.todoapp.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public Item getItem() {
        
        Item dep = new Item();
        dep.setId(102);
        dep.setContent("dependent item content");

        Item i = new Item();
        i.setContent("item content is this");
        i.setStatus(2);
        i.setDependentItems(new ArrayList<Item>(List.of(dep)));
        
        return i;
    }

    @GetMapping("/todos")
    public List<? extends Object> getAllTodos() {      
        List<Todo> l = (List<Todo>) todoService.listAll();
        for(int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i).getName());
        }
        return todoService.listAll();
    }
}