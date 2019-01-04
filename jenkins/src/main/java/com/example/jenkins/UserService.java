package com.example.jenkins;

import java.util.concurrent.CompletionStage;

public interface UserService {
    public CompletionStage<Integer> save(User user);
}
