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
import com.neptune.movieonline.adapters.CommentRecyclerViewAdapter;
import com.neptune.movieonline.models.Comment;
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

public class CommentListFragment extends Fragment {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private CommentRecyclerViewAdapter adapter;

    public static CommentListFragment newInstance() {
        return new CommentListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = new CommentRecyclerViewAdapter(getActivity(), new ArrayList<Comment>());
        recyclerView.setAdapter(adapter);
    }

    public void fetchComments(int movieId) {
        GsonRequest<Comment[]> commentRequest = MovieRequest.getComments(movieId,
                new Response.Listener<Comment[]>() {
                    @Override
                    public void onResponse(Comment[] response) {
                        adapter.setItems(Arrays.asList(response));
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(commentRequest);
    }
}
