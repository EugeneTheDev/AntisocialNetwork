package com.styleru.eugene.antisocialnetwork.domain.interactor.funcinterfaces;

@FunctionalInterface
public interface Failure {

    void onFailure(String message);

}
