package com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces;


@FunctionalInterface
public interface Result<T> {

    void onResult(T object);

}
