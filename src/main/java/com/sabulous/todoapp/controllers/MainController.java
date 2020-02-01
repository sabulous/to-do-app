package com.sabulous.todoapp.controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sabulous.todoapp.model.Item;
import com.sabulous.todoapp.model.Todo;
import com.sabulous.todoapp.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        // System.out.println("ALL TODO LISTS START");
        // for(int i = 0; i < l.size(); i++) {
        //     l.get(i).print();
        // }
        // System.out.println("ALL TODO LISTS END");

        return todoService.listAll();
    }

    @PostMapping("todos/add")
    public Todo addTodo(@RequestBody Todo todo) {
        // System.out.print("ADDED : ");
        // todo.print();
        return todoService.saveOrUpdate(todo);
    }

    @DeleteMapping("todos/delete/{id}")
    public boolean deleteTodo(@PathVariable Integer id) {
        todoService.delete(id);
        return true;
    }

    @PatchMapping("todos/update/{id}")
    public boolean updateTodoDetails(@PathVariable Integer id, @RequestBody Map<String, Object> fields) {
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
        // System.out.println("HERE IS THE PATCHED TODO : ");
        // todo.print();
        todoService.saveOrUpdate(todo);
        return true;
    }

}