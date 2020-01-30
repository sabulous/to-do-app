package com.sabulous.todoapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sabulous.todoapp.model.Todo;
import com.sabulous.todoapp.model.User;
import com.sabulous.todoapp.repositories.TodoRepository;
import com.sabulous.todoapp.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    private UserRepository userRepository;
    private TodoRepository todoRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> listAll() {
        List<Todo> todos = new ArrayList<>();
        todoRepository.findAll().forEach(todos::add);
        return todos;
    }

    @Override
    public Todo getById(Integer id) {
        
        return null;
    }

    @Override
    public Todo saveOrUpdate(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void delete(Integer id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<Todo> listTodosByUsername(String username) {

        List<Todo> todos = (List<Todo>)todoRepository.findAll();

        return todos.stream().filter(todo -> todo.getCreatedBy().equals(username)).collect(Collectors.toList());
    }

}