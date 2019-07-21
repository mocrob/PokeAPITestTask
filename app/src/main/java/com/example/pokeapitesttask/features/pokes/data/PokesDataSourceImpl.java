package com.example.pokeapitesttask.features.pokes.data;

import com.example.pokeapitesttask.features.pokes.domain.model.PokeList;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;
import com.example.pokeapitesttask.network.Carry;
import com.example.pokeapitesttask.network.DefaultCallback;

import java.util.List;

public final class PokesDataSourceImpl implements PokesDataSource {
    private final PokesAPI pokesAPI;

    public PokesDataSourceImpl(PokesAPI pokesAPI) {
        this.pokesAPI = pokesAPI;
    }

    @Override
    public void getPokes(int offsets, Carry<PokeList> carry) {
        pokesAPI.getPokeList(offsets).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getPokeById(String PokeId, Carry<Pokemon> carry) {
        pokesAPI.getPokeById(PokeId).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getPokeByName(String PokeName, Carry<Pokemon> carry) {
        pokesAPI.getPokeByName(PokeName).enqueue(new DefaultCallback(carry));
    }
}
