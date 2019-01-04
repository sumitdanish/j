package com.example.jenkins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionStage;

@Service
public class ServiceImpl implements UserService {

  @Autowired UserDAO userDAO;

  @Override
  public CompletionStage<User> save(User user) {
    return userDAO.insert(user);
  }
}
