package com.korzhuevadaria.catpic.ui.activities;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.korzhuevadaria.catpic.R;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.item_title));

        /* Получение информации об элементе и размещение её на экране */
        int postion = getIntent().getIntExtra(EXTRA_POSITION,0);
        Resources resources = getResources();

        TextView placeDetail = (TextView) findViewById(R.id.place_detail);

        String[] placeLocations = resources.getStringArray(R.array.pic_date);
        TextView placeLocation =  (TextView) findViewById(R.id.pic_date);
        placeLocation.setText(placeLocations[postion % placeLocations.length]);

        /* Размещение изображения */
        TypedArray placePictures = resources.obtainTypedArray(R.array.pic_picture);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        placePictures.recycle();
    }
}