package com.example.jenkins;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CustomDeferredResult {
    public static <T> DeferredResult<T> processDeferredResult(CompletableFuture<T> future) {
        DeferredResult<T> deferredResult = new DeferredResult<>(600000l);
        future
                .thenAccept(deferredResult::setResult)
                .whenComplete(onException(deferredResult::setErrorResult));
        return deferredResult;
    }

    public static BiConsumer<Object, Throwable> onException(Consumer<Throwable> throwableConsumer) {
        return (object, throwable) -> {
            if (throwable != null) {
                throwableConsumer.accept(getUnwrappedException(throwable));
            }
        };
    }

    public static Throwable getUnwrappedException(Throwable throwable) {
        while (throwable instanceof CompletionException || throwable instanceof ExecutionException) {
            throwable = throwable.getCause();
        }
        return throwable;
    }
}
