package com.example.kevin.catch_my_beer.models;

import java.util.List;

/**
 * Created by kevin on 15/02/2018.
 */

public class BeerBar {

    private List<BeerResult> results;
    private String status;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<BeerResult> getResults() {
        return results;
    }

    public void setResults(List<BeerResult> results) {
        this.results = results;
    }
}
