package com.neptune.movieonline.fragments;

import android.app.Fragment;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Movie;

import java.util.List;

import butterknife.BindView;

public class SearchListFragment extends Fragment {

    @BindView(R.id.recyclerViewSearch) RecyclerView recyclerView;
    private List<Movie> movies;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
