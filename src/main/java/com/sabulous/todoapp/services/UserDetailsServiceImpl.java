package com.sabulous.todoapp.services;

import com.sabulous.todoapp.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements IUserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getEncryptedPassword(), u.getAuthorities());
    }

    public Integer getLoggedInUserId() {
        return getLoggedInUser().getId();
    }

    public User getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = ((UserDetails)principal).getUsername();

        User loggedIn = userService.findByUsername(username);
        
        return loggedIn;
    }

}
