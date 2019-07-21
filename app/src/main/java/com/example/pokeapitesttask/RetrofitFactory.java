package com.example.pokeapitesttask;

import android.content.Context;

import com.example.pokeapitesttask.network.RetrofitProvider;
import com.example.pokeapitesttask.network.SessionInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

final class RetrofitFactory {
    static RetrofitProvider createRetrofitProvider(Context context) {
        final HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //final SessionDataSource dataSource = new SessionDataSourceImpl(context);
        //final SessionInterceptor sessionInterceptor = new SessionInterceptor(dataSource);
        final SessionInterceptor sessionInterceptor = new SessionInterceptor();

        final List<Interceptor> interceptorList = new ArrayList<>();
        interceptorList.add(logInterceptor);
        interceptorList.add(sessionInterceptor);

        return new RetrofitProvider(interceptorList);
    }
}
