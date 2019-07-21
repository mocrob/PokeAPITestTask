package com.example.pokeapitesttask.features.pokes.data;

import com.example.pokeapitesttask.features.pokes.domain.model.PokeList;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokesAPI {
    @GET("pokemon?limit=30")
    Call<PokeList> getPokeList(@Query("offset") int offsets);

    @GET("pokemon/{pokeId}")
    Call<Pokemon> getPokeById(@Path("pokeId") String pokeId);

    @GET("pokemon/{pokeName}")
    Call<Pokemon> getPokeByName(@Path("pokeName") String pokeName);
}
