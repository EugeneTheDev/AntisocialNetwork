package com.styleru.eugene.antisocialnetwork.domain.callbacks;

@FunctionalInterface
public interface Failure {

    void onFailure(ErrorType type);

}
