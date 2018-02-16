package com.example.kevin.catch_my_beer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageViewPhoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageViewPhoto = (findViewById(R.id.imageViewPhoto));

        Picasso.with(DetailsActivity.this).load("http://hospibuz.com/wp-content/uploads/2017/12/Hard-Rock-Cafe.jpg").into(imageViewPhoto);
    }
}
