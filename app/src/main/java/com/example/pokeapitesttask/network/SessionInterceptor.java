package com.example.pokeapitesttask.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class SessionInterceptor implements Interceptor {
    //private static final String HEADER_USER_ID = "userId";

    //private final SessionDataSource dataSource;

    /*public SessionInterceptor(SessionDataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    public  SessionInterceptor(){}
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        //builder.header(HEADER_USER_ID, dataSource.getSessionId());
        return chain.proceed( builder.build());
    }
}
