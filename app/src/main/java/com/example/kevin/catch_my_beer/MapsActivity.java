package com.example.kevin.catch_my_beer;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kevin.catch_my_beer.models.BeerGeometry;
import com.example.kevin.catch_my_beer.models.Tag;
import com.example.kevin.catch_my_beer.utils.Adapter;
import com.example.kevin.catch_my_beer.utils.Constant;
import com.example.kevin.catch_my_beer.utils.FastDialog;
import com.example.kevin.catch_my_beer.utils.Network;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yalantis.filter.listener.FilterListener;
import com.yalantis.filter.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, FilterListener<Tag> {

    private static final String TAG ="tagSelect" ;
    private GoogleMap mMap;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private Filter<Tag> mFilter;
    //  ZoomControls zoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //information pour initier le filtre par tag
        mFilter =findViewById(R.id.filter);
        mFilter.setAdapter(new Adapter(MapsActivity.this, getTags()));
        mFilter.setListener(this);

        //the text to show when there's no selected items
        mFilter.setNoSelectedItemText(getString(R.string.str_all_selected));
        mFilter.build();

      /*  zoom = (ZoomControls) findViewById(R.id.zcZoom);
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
         });*/
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng paris = new LatLng(48.864716, 2.349014);
        mMap.addMarker(new MarkerOptions().position(paris).title("Ici c'est PARIS"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(paris));

        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    private void setMarker(BeerGeometry location, String title) {
        // Add a marker for beer and move the camera
        LatLng bar =  new LatLng(location.getLocation().getLat(), location.getLocation().getLng());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(bar).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bar));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Cette application a besoin de votre localisation", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public void onFiltersSelected(ArrayList<Tag> arrayList) {
        Log.e(TAG,"onFiltersOne");
    }

    @Override
    public void onNothingSelected() {
        Log.e(TAG, "onFiltersTwo");
    }

    @Override
    public void onFilterSelected(Tag tag) {
        Log.e(TAG,"tag select"+tag.getText());
        Log.e(TAG, "onFiltersThree");
        /*if(!tag.getText().isEmpty()){
            //TODO : verification d'une connexion Internet

            if(Network.isNetworkAvailable(MapsActivity.this)) {
                //TODO : requête au web service
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MapsActivity.this);
                String url = String.format(Constant.URL_GOOGLE_PLACE, editTextCity.getText().toString());

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e(TAG, "json: "+response);

                                getData(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String response = new String(error.networkResponse.data);

                        getData(response);

                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            } else {
                FastDialog.showDialog(MapsActivity.this, FastDialog.SIMPLE_DIALOG, "Vous devez être connecté");
            }
        } else {
            FastDialog.showDialog(MapsActivity.this, FastDialog.SIMPLE_DIALOG, "Vous devez renseigner une ville");
        }*/
    }

    @Override
    public void onFilterDeselected(Tag tag) {
        Log.e(TAG, "onFiltersFour");
    }

    public List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("bar"));
        tags.add(new Tag("bar2"));
        tags.add(new Tag("bar3"));
        tags.add(new Tag("bar4"));

        return tags;
    }
}
