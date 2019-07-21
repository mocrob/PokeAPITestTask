package com.example.pokeapitesttask.network;

public interface Carry<T> {
    void onSuccess(T result);

    void onFailure(Throwable throwable);
}
