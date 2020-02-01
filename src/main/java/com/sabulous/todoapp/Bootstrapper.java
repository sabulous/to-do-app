package com.sabulous.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.getDefault());
        LocalDate fromDate = LocalDate.now(ZoneId.of("Europe/Istanbul"));
        //String tempFromDate = fromDate.format(formatter);
        Calendar c = Calendar.getInstance(); 
        c.set(fromDate.getYear(), fromDate.getMonthValue(), fromDate.getDayOfMonth());
    
        list.add(
            tb.name("todo 1")
            .creationDate(new Date(c.getTimeInMillis()))
            .deadline(new Date(c.getTimeInMillis() + Long.valueOf("150000000000")))
            .completionDate(new Date(c.getTimeInMillis()))
            .createdBy("user")
            .status(1)
            .completed(false)
            .build()
        );

        TodoBuilder tb2 = new TodoBuilder();
        list.add(
            tb2.name("todo 2")
            .creationDate(new Date(c.getTimeInMillis()))
            .deadline(new Date(c.getTimeInMillis() + Long.valueOf("340000000000")))
            .completionDate(new Date(c.getTimeInMillis()))
            .createdBy("user 2")
            .status(0)
            .completed(true)
            .build()
        );

        TodoBuilder tb3 = new TodoBuilder();

        list.add(
            tb3.name("todo 3")
            .creationDate(new Date(c.getTimeInMillis()))
            .deadline(new Date(c.getTimeInMillis() + Long.valueOf("50000000000")))
            .completionDate(null)
            .createdBy("user 3")
            .status(-1)
            .completed(false)
            .build()
        );

        todoRepository.saveAll(list);
    }
    
}