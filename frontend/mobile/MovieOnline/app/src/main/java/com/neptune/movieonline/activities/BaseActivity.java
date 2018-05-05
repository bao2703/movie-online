package com.neptune.movieonline.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by Neptune on 4/8/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void startActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }
}
