
package com.sabulous.todoapp.services;

import com.sabulous.todoapp.model.User;

public interface UserService extends CRUDService<User> {
    User findByUsername(String username);
}