package com.example.pokeapitesttask.features.pokes.data;

import com.example.pokeapitesttask.features.pokes.domain.model.PokeList;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;
import com.example.pokeapitesttask.network.Carry;

import java.util.List;

public interface PokesRepository {

    void loadPokes(int offset, Carry<PokeList> carry);

    void loadPokeById(String PokeId, Carry<Pokemon> carry);

    void loadPokeByName(String PokeName, Carry<Pokemon> carry);
}
