package com.example.pokeapitesttask.features.pokes.data;

import com.example.pokeapitesttask.features.pokes.domain.model.PokeList;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;
import com.example.pokeapitesttask.network.Carry;

import java.util.List;

public final class PokesRepositoryImpl implements PokesRepository {

    private final PokesDataSource pokesDataSource;

    public PokesRepositoryImpl(PokesDataSource pokesDataSource) {
        this.pokesDataSource = pokesDataSource;
    }

    @Override
    public void loadPokes(int offset, Carry<PokeList> carry) {
        pokesDataSource.getPokes(offset,carry);
    }

    @Override
    public void loadPokeById(String PokeId, Carry<Pokemon> carry) {
        pokesDataSource.getPokeById(PokeId,carry);
    }

    @Override
    public void loadPokeByName(String PokeName, Carry<Pokemon> carry) {
        pokesDataSource.getPokeByName(PokeName, carry);
    }
}
