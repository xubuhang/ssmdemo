package com.truehang.dao;

import com.truehang.model.User;

public interface IUserDao {

    User selectUser(long id);

}