package com.example.jenkins.api;

import com.example.jenkins.CustomDeferredResult;
import com.example.jenkins.User;
import com.example.jenkins.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("test")
public class Api {

  @Autowired UserService userService;

  @PostMapping(value = "save")
  public DeferredResult<Integer> insert(@RequestBody User user) {
    return CustomDeferredResult.processDeferredResult(userService.save(user).toCompletableFuture());
  }
}
