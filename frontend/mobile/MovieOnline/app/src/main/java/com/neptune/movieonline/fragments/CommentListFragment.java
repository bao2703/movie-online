package com.neptune.movieonline.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neptune on 4/12/2018.
 */

public class CommentListFragment extends Fragment {

    @BindView(R.id.recyclerViewComment) RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.line_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        fetchComments();
    }

    private void fetchComments() {
        GsonRequest<Comment[]> commentRequest = MovieRequest.getComments(1,
                new Response.Listener<Comment[]>() {
                    @Override
                    public void onResponse(Comment[] response) {
                        recyclerView.setAdapter(new CommentRecyclerViewAdapter(Arrays.asList(response)));
                    }
                }, null);
        VolleyHelper.getInstance().addToRequestQueue(commentRequest);
    }
}
