package com.example.pokeapitesttask.features.pokes.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pokeapitesttask.R;
import com.example.pokeapitesttask.features.BaseActivity;
import com.example.pokeapitesttask.features.MvpPresenter;
import com.example.pokeapitesttask.features.MvpView;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity implements PokeListView {

    private PokeListPresenter presenter;
    private PokeAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private CheckBox checkBoxAttack;
    private CheckBox checkBoxDefence;
    private CheckBox checkBoxHp;
    private ProgressBar progressBar;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected  MvpPresenter<PokeListView> getPresenter() {
        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected MvpView getMvpView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        recyclerView = findViewById(R.id.pokes_recycle_view);
        floatingActionButton = findViewById(R.id.reloadPokeList);
        checkBoxAttack = findViewById(R.id.checkBoxAttack);
        checkBoxDefence = findViewById(R.id.checkBoxDefence);
        checkBoxHp = findViewById(R.id.checkBoxHp);
        progressBar = findViewById(R.id.pokes_progress);

        checkBoxAttack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBoxAttack.isChecked()){
                    //recyclerView.scrollToPosition(adapter.positionOfBestAttackPoke(presenter));
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                presenter.onReloadClicked(random.nextInt(934));
                recyclerView.scrollToPosition(0);
            }
        });

        adapter = new PokeAdapter(this, new PokeAdapter.SelectPokeListener() {
            @Override
            public void onPokeSelect(Pokemon pokemon) {
                presenter.onPokeSelected(pokemon);
            }
        });
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                //totalItemCount = recyclerView.getLayoutManager().getItemCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                //firstVisibleItem = recyclerView.getLayoutManager().findFirstVisibleItemPosition();
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        //previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    // Do something
                    current_page++;
                    //Toast.makeText(getBaseContext(),"This is end",Toast.LENGTH_LONG).show();
                    presenter.onReloadClicked(current_page*30);
                    recyclerView.scrollToPosition(0);

                    loading = true;
                }
            }
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPokeList(List<Pokemon> list) {
        adapter.setPokes(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        Log.d("Error",message);
    }

    @Override
    public void openFullPokeCard(Pokemon pokemon) {
        ShowPokeActivity.start(this,pokemon);
    }

    @Override
    public void showSuccess() {

    }
}
