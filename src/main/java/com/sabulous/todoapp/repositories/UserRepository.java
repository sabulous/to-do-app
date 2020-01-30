package com.sabulous.todoapp.repositories;

import com.sabulous.todoapp.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
}