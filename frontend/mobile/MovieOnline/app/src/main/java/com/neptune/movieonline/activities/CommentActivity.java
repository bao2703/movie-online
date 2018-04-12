package com.neptune.movieonline.activities;

import android.os.Bundle;
import android.widget.EditText;

import com.neptune.movieonline.R;

import butterknife.BindView;
import butterknife.OnClick;

public class CommentActivity extends BaseActivity {

    @BindView(R.id.editTextComment) EditText editTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
    }

    private void validateInput() {
    }

    @OnClick(R.id.buttonComment)
    public void onClickComment() {
        //TODO: Post comment to server here
    }

    public String getComment() {
        return editTextComment.getText().toString();
    }
}
