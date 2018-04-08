package com.neptune.movieonline.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.neptune.movieonline.R;
import com.neptune.movieonline.adapters.viewholders.CommentViewHolder;
import com.neptune.movieonline.models.Comment;

import java.util.List;

/**
 * Created by Neptune on 3/28/2018.
 */

public class CommentListAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private Context context;
    private List<Comment> commentList;

    public CommentListAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        final Comment item = commentList.get(position);

        holder.getTextViewContent().setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
