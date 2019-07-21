package com.example.pokeapitesttask.features.pokes.data;

import com.example.pokeapitesttask.features.pokes.domain.model.PokeList;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;
import com.example.pokeapitesttask.network.Carry;

import java.util.List;

public interface PokesDataSource {

    void getPokes(int offsets, Carry<PokeList> carry);

    void getPokeById(String PokeId, Carry<Pokemon> carry);

    void getPokeByName(String PokeName, Carry<Pokemon> carry);
}
