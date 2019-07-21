package com.example.pokeapitesttask.features.pokes.presentation;

import com.example.pokeapitesttask.features.MvpView;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;

import java.util.List;

public interface PokeListView extends MvpView {
    void showProgress();

    void hideProgress();

    void showPokeList(List<Pokemon> list);

    void showError(String message);

    void openFullPokeCard(Pokemon pokemon);

    void showSuccess();
}
