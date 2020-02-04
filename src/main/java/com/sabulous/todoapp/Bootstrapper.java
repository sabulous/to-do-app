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
import com.sabulous.todoapp.model.User;
import com.sabulous.todoapp.repositories.TodoRepository;
import com.sabulous.todoapp.services.TodoBuilder;
import com.sabulous.todoapp.services.TodoService;
import com.sabulous.todoapp.services.UserService;

@Component
public class Bootstrapper implements ApplicationListener<ContextRefreshedEvent> {
    private TodoRepository todoRepository;
    private TodoService todoService;
    private UserService userService;

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }  

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadTodos();
        loadUsers();
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
            .createdBy("user")
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
            .createdBy("user")
            .status(-1)
            .completed(false)
            .build()
        );

        todoRepository.saveAll(list);
    }

    private void loadUsers() {        
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("username");
        user1.setEmail("email");
        user1.setPassword("userPw");

        userService.saveOrUpdate(user1);
        
        User user2 = new User();
        user2.setId(2);
        user2.setEmail("user2Email");
        user2.setUsername("user2name");
        user2.setPassword("user2Pw");

        userService.saveOrUpdate(user2);
    }
    
}