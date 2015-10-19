package com.example.yhuang.scavengerhunt.onClickListeners.ClueFetch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

import com.example.yhuang.scavengerhunt.R;

/**
 * Created by yhuang on 10/19/2015.
 */
public class NextListener implements View.OnClickListener{

    private Activity m_activity;

    public NextListener(Activity activity) {
        m_activity = activity;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(m_activity);
        alertDialogBuilder.setMessage(R.string.clue_switch_message);
        alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //next()
            }
        });
        alertDialogBuilder.setNegativeButton(R.string.go_back, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //nothing
            }
        });
        alertDialogBuilder.show();
    }
}
