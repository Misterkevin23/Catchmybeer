package com.example.kevin.catch_my_beer.models;

import com.yalantis.filter.model.FilterModel;

/**
 * Created by Junior on 15/02/2018.
 */

public class Tag implements FilterModel {
    private String text;

    public Tag(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
