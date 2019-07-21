package com.example.pokeapitesttask.network;

import com.example.pokeapitesttask.Exception.EmptyBodyException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class DefaultCallback<T> implements Callback<T> {
    private final Carry<T> carry;

    public DefaultCallback(final Carry<T> carry) {
        this.carry = carry;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (body != null) {
            carry.onSuccess(body);
        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        carry.onFailure(t);
    }
}
