package com.sabulous.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.sabulous.todoapp.model.Todo;
import com.sabulous.todoapp.repositories.TodoRepository;
import com.sabulous.todoapp.services.TodoBuilder;
import com.sabulous.todoapp.services.TodoService;

@Component
public class Bootstrapper implements ApplicationListener<ContextRefreshedEvent> {
    private TodoRepository todoRepository;
    private TodoService todoService;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }  

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadTodos();
    }

    private void loadTodos() {
        final long dayLen = 86400000;
        List<Todo> list = new ArrayList<>();

        TodoBuilder tb = new TodoBuilder();
        list.add(
            tb.name("todo 1")
            .creationDate(new Date(System.currentTimeMillis() - dayLen * 10))
            .deadline(new Date(System.currentTimeMillis() + dayLen * 5))
            .completionDate(new Date(System.currentTimeMillis() - dayLen * 2))
            .createdBy("user")
            .status(1)
            .build()
        );

        TodoBuilder tb2 = new TodoBuilder();
        list.add(
            tb2.name("todo 2")
            .creationDate(new Date(System.currentTimeMillis() - dayLen * 40))
            .deadline(new Date(System.currentTimeMillis() + dayLen * 2))
            .completionDate(new Date(System.currentTimeMillis() - dayLen * 6))
            .createdBy("user 2")
            .status(0)
            .build()
        );

        TodoBuilder tb3 = new TodoBuilder();

        list.add(
            tb3.name("todo 3")
            .creationDate(new Date(System.currentTimeMillis() - dayLen * 1))
            .deadline(new Date(System.currentTimeMillis() + dayLen * 0))
            .completionDate(null)
            .createdBy("user 3")
            .status(-1)
            .build()
        );

        todoRepository.saveAll(list);
    }
    
}