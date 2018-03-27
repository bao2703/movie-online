package com.neptune.movieonline.utils.helpers;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by Neptune on 3/16/2018.
 */

public abstract class DialogHelper {

    public static AlertDialog.Builder createAlertDialog(Context context) {
        return new AlertDialog.Builder(context);
    }

    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
