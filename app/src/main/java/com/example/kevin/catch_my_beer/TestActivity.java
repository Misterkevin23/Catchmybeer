package com.example.kevin.catch_my_beer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kevin.catch_my_beer.models.Tag;
import com.example.kevin.catch_my_beer.utils.Adapter;
import com.yalantis.filter.listener.FilterListener;
import com.yalantis.filter.widget.Filter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements FilterListener<Tag> {

    private Filter<Tag> mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mFilter = (Filter<Tag>) findViewById(R.id.filter);
        mFilter.setAdapter(new Adapter(TestActivity.this, getTags()));
        mFilter.setListener(this);

        //the text to show when there's no selected items
        mFilter.setNoSelectedItemText(getString(R.string.str_all_selected));
        mFilter.build();
    }

    @Override
    public void onFiltersSelected(ArrayList<Tag> arrayList) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onFilterSelected(Tag tag) {

    }

    @Override
    public void onFilterDeselected(Tag tag) {

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
