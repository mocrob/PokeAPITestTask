package com.example.pokeapitesttask.features.pokes.presentation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokeapitesttask.R;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeHolder> {
    private final List<Pokemon> pokemons = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectPokeListener selectPokeListener;
    private PokeListPresenter presenter;

    PokeAdapter(Context context, SelectPokeListener selectPokeListener){
        inflater = LayoutInflater.from(context);
        this.selectPokeListener = selectPokeListener;
    }
    @NonNull
    @Override
    public PokeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.poke_item,parent,false);
        return new PokeHolder(itemView, selectPokeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PokeHolder holder, int position) {
        holder.bind(pokemons.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void setPokes(List<Pokemon> pokemonList){
        pokemons.clear();
        pokemons.addAll(pokemonList);
        notifyDataSetChanged();
    }

    class PokeHolder extends RecyclerView.ViewHolder{

        private final TextView nameView;
        private final SelectPokeListener selectPokeListener;
        private final ImageView imageView;

        public PokeHolder(@NonNull View itemView, SelectPokeListener selectPokeListener) {
            super(itemView);
            this.selectPokeListener = selectPokeListener;
            nameView = itemView.findViewById(R.id.name_item);
            imageView = itemView.findViewById(R.id.imageViewCard);
        }

        void bind(final Pokemon pokemon){
            String SpriteUrl;
            String id = pokemon.getUrl().substring(34, pokemon.getUrl().length()-1);
            SpriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                    + id
                    + ".png";
            nameView.setText(pokemon.getName());
            new DownloadImageTask(imageView)
                    .execute(SpriteUrl);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPokeListener.onPokeSelect(pokemon);
                }
            });
        }
    }

    interface SelectPokeListener{
        void onPokeSelect(Pokemon pokemon);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public int positionOfBestAttackPoke(PokeListPresenter presenter){
        this.presenter=presenter;
        double att=0;
        int pos=0;
        for (int i=0;i<pokemons.size();i++){
            double curent = presenter.mostAttack(pokemons.get(i));
            if(curent>att){att=curent;pos=i;}
        }
        return pos;
    }
}
