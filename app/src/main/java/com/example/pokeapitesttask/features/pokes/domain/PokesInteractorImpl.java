package com.example.pokeapitesttask.features.pokes.domain;

import com.example.pokeapitesttask.features.pokes.data.PokesRepository;
import com.example.pokeapitesttask.features.pokes.domain.model.PokeList;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;
import com.example.pokeapitesttask.network.Carry;

import java.util.List;

public final class PokesInteractorImpl implements PokesInteractor {

    private final PokesRepository repository;

    public PokesInteractorImpl(PokesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadPokes(int offset, Carry<PokeList> carry) {
        repository.loadPokes(offset, carry);
    }

    @Override
    public void loadPokeById(String PokeId, Carry<Pokemon> carry) {
        repository.loadPokeById(PokeId, carry);
    }

    @Override
    public void loadPokeByName(String PokeName, Carry<Pokemon> carry) {
        repository.loadPokeByName(PokeName, carry);
    }
}
