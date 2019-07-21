package com.example.pokeapitesttask.features.pokes.presentation;

import android.content.Context;

import com.example.pokeapitesttask.App;
import com.example.pokeapitesttask.features.pokes.data.PokesAPI;
import com.example.pokeapitesttask.features.pokes.data.PokesDataSource;
import com.example.pokeapitesttask.features.pokes.data.PokesDataSourceImpl;
import com.example.pokeapitesttask.features.pokes.data.PokesRepository;
import com.example.pokeapitesttask.features.pokes.data.PokesRepositoryImpl;
import com.example.pokeapitesttask.features.pokes.domain.PokesInteractor;
import com.example.pokeapitesttask.features.pokes.domain.PokesInteractorImpl;

public final class PresenterFactory {

    static PokeListPresenter createPresenter(Context context){
        final PokesAPI api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(PokesAPI.class);
        final PokesDataSource dataSource = new PokesDataSourceImpl(api);
        final PokesRepository repository = new PokesRepositoryImpl(dataSource);
        final PokesInteractor interactor = new PokesInteractorImpl(repository);
        return new PokeListPresenter(interactor);
    }
}
