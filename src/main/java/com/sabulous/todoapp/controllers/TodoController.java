package com.sabulous.todoapp.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.sabulous.todoapp.model.Todo;
import com.sabulous.todoapp.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<? extends Object> getAllTodos() {      
        return todoService.listAll();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoService.saveOrUpdate(todo);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable Integer id) {
        todoService.delete(id);
        return;
    }

    @PatchMapping("{id}")
    public Todo updateTodoDetails(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
        Todo todo = todoService.getById(id);

        // Remove id from request, we don't ever want to change the id.
        // This is not necessary, you can just do it to save time on the reflection
        // loop used below since we checked the id above
        fields.remove("id");

        fields.forEach((k, v) -> {
            // use reflection to get field k on object and set it to value v
            // Change Claim.class to whatver your object is: Object.class
            Field field = ReflectionUtils.findField(Todo.class, k); // find field in the Todo class
            field.setAccessible(true); 
            ReflectionUtils.setField(field, todo, v); // set given field for defined object to value V
        });
        return todoService.saveOrUpdate(todo);
    }

}