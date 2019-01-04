package com.example.jenkins;

import com.example.jenkins.errorHandler.UserDetailsAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

@Service
public class ServiceImpl implements UserService {

  @Autowired UserDAO userDAO;

  @Override
  public CompletionStage<Integer> save(User user) {
    return userDAO
        .insert(user)
        .exceptionally(
            ex -> {
              Throwable t = ex.getCause();
              if (t instanceof DuplicateKeyException) {
                throw new UserDetailsAlreadyExistException(
                    String.format("EmailID %s already exist", user.getEmailID()));
              }
              System.out.println("ex = " + t);
              throw new CompletionException(t);
            });
  }
}
