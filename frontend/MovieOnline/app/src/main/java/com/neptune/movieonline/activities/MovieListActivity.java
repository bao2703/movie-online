package com.neptune.movieonline.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.MovieListAdapter;
import com.neptune.movieonline.utils.helpers.VolleyHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerViewMovie)
    RecyclerView recyclerViewMovie;

    MovieListAdapter movieListAdapter;
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        VolleyHelper.initialize(this);
        ButterKnife.bind(this);

        data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(String.valueOf(i));
        }
        movieListAdapter = new MovieListAdapter(data);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerViewMovie.setLayoutManager(layoutManager);
        recyclerViewMovie.setAdapter(movieListAdapter);
    }
}
