package com.example.kevin.catch_my_beer.models;

import java.io.Serializable;

/**
 * Created by kevin on 15/02/2018.
 */

public class BeerResult implements Serializable {
    private String name;
    private String rating; // Nom de l'établissment
    private String vicinity; // Nom de la rue
    private BeerGeometry geometry; // localisation
    private Opening_hours opening_hours;  // Hoaire d'ouverture

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public BeerGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(BeerGeometry geometry) {
        this.geometry = geometry;
    }

    public Opening_hours getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(Opening_hours opening_hours) {
        this.opening_hours = opening_hours;
    }

    private class Opening_hours implements Serializable{
        private boolean open_now;

        public boolean isOpen_now() {
            return open_now;
        }

        public void setOpen_now(boolean open_now) {
            this.open_now = open_now;
        }
    }

}
