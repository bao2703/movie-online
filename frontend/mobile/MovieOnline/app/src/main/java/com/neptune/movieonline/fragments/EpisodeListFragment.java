package com.neptune.movieonline.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.EpisodeRecyclerViewAdapter;
import com.neptune.movieonline.models.Episode;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.GsonRequest;
import com.neptune.movieonline.utils.requests.MovieRequest;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 4/12/2018.
 */

public class EpisodeListFragment extends Fragment implements Response.Listener<Episode[]> {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private EpisodeRecyclerViewAdapter adapter;

    public static EpisodeListFragment newInstance() {
        return new EpisodeListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episode_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    public void fetchEpisodes(Integer movieId) {
        GsonRequest<Episode[]> moviesRequest = MovieRequest.getEpisodes(movieId, this, null);
        VolleyHelper.getInstance().addToRequestQueue(moviesRequest);
    }

    @Override
    public void onResponse(Episode[] response) {
        adapter = new EpisodeRecyclerViewAdapter(getActivity(), new ArrayList<>(Arrays.asList(response)));
        recyclerView.setAdapter(adapter);
    }
}
