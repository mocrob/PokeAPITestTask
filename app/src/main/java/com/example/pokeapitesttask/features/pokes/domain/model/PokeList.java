package com.example.pokeapitesttask.features.pokes.domain.model;

import java.util.List;

public final class PokeList {
    List<Pokemon> results;

    public List<Pokemon> getList() {
        return results;
    }

    public void setList(List<Pokemon> list) {
        this.results = list;
    }
}
