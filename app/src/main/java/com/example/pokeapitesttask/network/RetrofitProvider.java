package com.example.pokeapitesttask.network;

import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitProvider {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

    private final Retrofit retrofit;

    public RetrofitProvider(List<Interceptor> interceptorList) {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient(interceptorList))
                .build();
    }

    private OkHttpClient createClient(List<Interceptor> interceptorList) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        for (Interceptor interceptor: interceptorList) {
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
