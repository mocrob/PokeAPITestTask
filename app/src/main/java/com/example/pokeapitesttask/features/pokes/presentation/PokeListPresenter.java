package com.example.pokeapitesttask.features.pokes.presentation;

import com.example.pokeapitesttask.features.MvpPresenter;
import com.example.pokeapitesttask.features.pokes.domain.PokesInteractor;
import com.example.pokeapitesttask.features.pokes.domain.model.PokeList;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;
import com.example.pokeapitesttask.network.Carry;

import java.util.List;

public final class PokeListPresenter extends MvpPresenter<PokeListView> {

    public int counterOffsets = 0;
    private final PokesInteractor interactor;

    public PokeListPresenter(PokesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady(){
        loadPokes();
    }

    private void loadPokes(){
        view.showProgress();
        interactor.loadPokes(counterOffsets * 30, new Carry<PokeList>() {
            @Override
            public void onSuccess(PokeList result) {
                view.showPokeList(result.getList());
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }
    double att;
    double mostAttack(Pokemon pokemon){

        view.showProgress();
        interactor.loadPokeByName(pokemon.getName(), new Carry<Pokemon>() {
            @Override
            public void onSuccess(Pokemon result) {
                view.hideProgress();
                att = result.getAttack();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
        return att;
    }

    void onPokeSelected(Pokemon pokemon){
        view.showProgress();
        interactor.loadPokeByName(pokemon.getName(), new Carry<Pokemon>() {
            @Override
            public void onSuccess(Pokemon result) {
                view.hideProgress();
                view.openFullPokeCard(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    void onReloadClicked(int counterOffsets){
        this.counterOffsets = counterOffsets;
        view.showProgress();
        interactor.loadPokes(counterOffsets, new Carry<PokeList>() {
            @Override
            public void onSuccess(PokeList result) {
                view.hideProgress();
                view.showPokeList(result.getList());
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }
}
