package com.neptune.movieonline.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.neptune.movieonline.R;
import com.neptune.movieonline.fragments.CommentListFragment;
import com.neptune.movieonline.models.Comment;
import com.neptune.movieonline.models.Movie;
import com.neptune.movieonline.utils.constants.Extra;
import com.neptune.movieonline.utils.helpers.DialogHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.CommentRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;

import butterknife.BindView;
import butterknife.OnClick;

import static com.neptune.movieonline.R.id.fragment_container;

public class CommentActivity extends BaseActivity {

    @BindView(R.id.editTextComment) EditText editTextComment;

    Movie MOVIE;
    private ProgressDialog progressDialog;
    private CommentListFragment commentListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setData();
        initViews();
    }

    private void initViews() {
        commentListFragment = CommentListFragment.newInstance(MOVIE.getId());
        getFragmentManager()
                .beginTransaction()
                .replace(fragment_container, commentListFragment)
                .commit();
    }

    private void setData() {
        MOVIE = (Movie) getIntent().getSerializableExtra(Extra.MOVIE);
        setTitle(MOVIE.getName());
    }

    private boolean validateInput() {
        boolean result = true;

        final String comment = getComment();

        if (comment.isEmpty()) {
            editTextComment.setError(getString(R.string.error_field_required));
            result = false;
        }
        if (comment.length() > 200) {
            editTextComment.setError(getString(R.string.error_comment_invalid));
            result = false;
        }
        return result;
    }

    @OnClick(R.id.buttonComment)
    public void onClickComment() {
        if (!validateInput()) {
            return;
        }

        progressDialog = DialogHelper.createProgressDialog(CommentActivity.this);
        progressDialog.show();

        Comment payload = new Comment();
        payload.setContent(getComment());
        payload.setUserId(1);
        payload.setMovieId(MOVIE.getId());

        GsonRequest<String> commentRequest = CommentRequest.createComment(payload,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        commentListFragment.fetchComments();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                    }
                });
        VolleyHelper.getInstance().addToRequestQueue(commentRequest);
    }

    public String getComment() {
        return editTextComment.getText().toString();
    }
}
