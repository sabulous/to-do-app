package com.sabulous.todoapp.repositories;

import com.sabulous.todoapp.model.Todo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {

}