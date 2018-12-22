package com.styleru.eugene.antisocialnetwork.presentation.funcinterfaces;

@FunctionalInterface
public interface Failure {

    void onFailure(String message);

}
