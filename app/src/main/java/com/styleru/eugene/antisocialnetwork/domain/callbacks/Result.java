package com.styleru.eugene.antisocialnetwork.domain.callbacks;


@FunctionalInterface
public interface Result<T> {

    void onResult(T object);

}
