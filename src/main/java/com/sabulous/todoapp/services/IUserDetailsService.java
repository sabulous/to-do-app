package com.sabulous.todoapp.services;

import com.sabulous.todoapp.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {
    public Integer getLoggedInUserId();
    public User getLoggedInUser();
}