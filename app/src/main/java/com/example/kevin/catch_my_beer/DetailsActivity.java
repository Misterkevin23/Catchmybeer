package com.example.kevin.catch_my_beer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    // déclaration de variable

    private ImageView imageViewPhoto = null;
    private TextView  textViewTitle;
    private LinearLayout linearLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageViewPhoto = (findViewById(R.id.imageViewPhoto));
        linearLayoutContainer = (findViewById(R.id.linearLayoutContainer));
        textViewTitle = (findViewById(R.id.textViewTitle));

        Picasso.with(DetailsActivity.this).load("http://hospibuz.com/wp-content/uploads/2017/12/Hard-Rock-Cafe.jpg").into(imageViewPhoto);
    }
}
