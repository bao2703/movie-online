package com.neptune.movieonline.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.neptune.movieonline.R;
import com.neptune.movieonline.models.Comment;
import com.neptune.movieonline.utils.helpers.DialogHelper;
import com.neptune.movieonline.utils.helpers.VolleyHelper;
import com.neptune.movieonline.utils.requests.CommentRequest;
import com.neptune.movieonline.utils.requests.GsonRequest;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity {

    @BindView(R.id.editTextComment) EditText editTextComment;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }

    private boolean validateInput() {
        boolean result = true;

        final String comment = getComment();

        if(comment.isEmpty()) {
            editTextComment.setError(getString(R.string.error_field_required));
            result = false;
        }
        if(comment.length() > 200) {
            editTextComment.setError(getString(R.string.error_content_invalid));
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

        GsonRequest<String> commentRequest = CommentRequest.comments(payload,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        FragmentManager fragmentManager = getFragmentManager();
                        Fragment fragment =  fragmentManager.findFragmentById(R.id.fragmentCommentList);
                        
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
