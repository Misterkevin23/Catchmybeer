package com.example.kevin.catch_my_beer.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.example.kevin.catch_my_beer.models.Tag;
import com.yalantis.filter.adapter.FilterAdapter;
import com.yalantis.filter.widget.FilterItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter extends FilterAdapter<Tag> {

    private  Context context;

        public Adapter(Context context, @NotNull List<? extends Tag> items) {
            super(items);

            this.context = context;
        }

    @NotNull
    @Override
    public FilterItem createView(int position, Tag item) {
        FilterItem filterItem = new FilterItem(context);

        filterItem.setStrokeColor(ContextCompat.getColor(context, android.R.color.black));
        filterItem.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        filterItem.setCheckedTextColor(ContextCompat.getColor(context, android.R.color.white));
        filterItem.setColor(ContextCompat.getColor(context, android.R.color.white));
        filterItem.setCheckedColor(ContextCompat.getColor(context, android.R.color.black));
        filterItem.setText(item.getText());
        filterItem.deselect();

        return filterItem;
    }
}