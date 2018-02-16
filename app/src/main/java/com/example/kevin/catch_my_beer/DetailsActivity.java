package com.example.kevin.catch_my_beer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kevin.catch_my_beer.models.BeerResult;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    // déclaration de variable

    private ImageView imageViewPhoto = null;
    private TextView  textViewTitle;
    private LinearLayout linearLayoutContainer;
    private BeerResult beerPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageViewPhoto = (findViewById(R.id.imageViewPhoto));
        linearLayoutContainer = (findViewById(R.id.linearLayoutContainer));
        textViewTitle = (findViewById(R.id.textViewTitle));

        Picasso.with(DetailsActivity.this).load("http://hospibuz.com/wp-content/uploads/2017/12/Hard-Rock-Cafe.jpg").into(imageViewPhoto);

        if(getIntent().getExtras() != null) {
            if (getIntent().getExtras().get("beerData") instanceof BeerResult) {

                beerPlace = (BeerResult) getIntent().getExtras().get("beerData");

                textViewTitle.setText(beerPlace.getName());


                if(!beerPlace.getRating().isEmpty()) {
                    linearLayoutContainer.addView(getLayoutInformations(R.drawable.logo_beer, beerPlace.getRating()));
                }

                if(!beerPlace.getVicinity().isEmpty()) {
                    linearLayoutContainer.addView(getLayoutInformations(R.drawable.logo_location, beerPlace.getVicinity()));
                }

                if(beerPlace.getOpening_hours() != null && beerPlace.getOpening_hours().isOpen_now()) {
                    linearLayoutContainer.addView(getLayoutInformations(R.drawable.logo_hour, "Ouvert"));
                } else {
                    linearLayoutContainer.addView(getLayoutInformations(R.drawable.logo_hour, "Fermé"));

                }


            }
        }
    }

    private View getLayoutInformations(int logo, String text){
        View viewInformations = LayoutInflater.from(DetailsActivity.this).inflate(R.layout.item_information, null);

        ImageView imageViewIcon = viewInformations.findViewById(R.id.imageViewIcon);
        TextView textViewInformation = viewInformations.findViewById(R.id.textViewInformation);

        imageViewIcon.setImageResource(logo);
        textViewInformation.setText(text);

        return viewInformations;
    }

}
