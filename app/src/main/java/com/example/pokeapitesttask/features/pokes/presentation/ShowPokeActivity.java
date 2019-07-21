package com.example.pokeapitesttask.features.pokes.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokeapitesttask.R;
import com.example.pokeapitesttask.features.pokes.domain.model.Pokemon;

import java.io.InputStream;

public class ShowPokeActivity extends AppCompatActivity {

    private ImageView pokeSprite;
    private TextView heightPoke;
    private TextView weightPoke;
    private TextView typePoke;
    private TextView attackPoke;
    private TextView defencePoke;
    private TextView hpPoke;
    public static void start(final Context context, Pokemon pokemon) {
        Intent intent = new Intent(context, ShowPokeActivity.class);
        intent.putExtra(Pokemon.class.getSimpleName(), pokemon);
        context.startActivity(intent);
    }

    private Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_poke);
        Bundle arguments = getIntent().getExtras();
        pokemon = (Pokemon) arguments.getSerializable(Pokemon.class.getSimpleName());
        pokeSprite = findViewById(R.id.imageView);
        heightPoke = findViewById(R.id.textViewHeight);
        weightPoke = findViewById(R.id.textViewWeight);
        typePoke = findViewById(R.id.textViewType);
        attackPoke = findViewById(R.id.textViewAttack);
        defencePoke = findViewById(R.id.textViewDefence);
        hpPoke = findViewById(R.id.textViewHp);

        heightPoke.setText(String.valueOf(pokemon.getHeight()));
        weightPoke.setText(String.valueOf(pokemon.getWeight()));
        typePoke.setText(pokemon.getTypesOfPokemon());
        attackPoke.setText(String.valueOf(pokemon.getAttack()));
        defencePoke.setText(String.valueOf(pokemon.getDefence()));
        hpPoke.setText(String.valueOf(pokemon.getHp()));

        String SpriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
                + String.valueOf(pokemon.getId())
                + ".png";
        new DownloadImageTask(pokeSprite)
                .execute(SpriteUrl);
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
}
