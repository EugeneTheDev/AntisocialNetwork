package com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces;

@FunctionalInterface
public interface Success<T> {

    void onSuccess(T object);

}
