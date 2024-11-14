package com.murach.dao;

import com.murach.dto.User;

public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
