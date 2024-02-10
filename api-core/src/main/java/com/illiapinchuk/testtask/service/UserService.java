package com.illiapinchuk.testtask.service;

import com.illiapinchuk.testtask.persistence.entity.User;

public interface UserService {

  User getUserByLogin(String login);
}
