package com.example.yhuang.scavengerhunt.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.yhuang.scavengerhunt.Fragments.ClueFetch;
import com.example.yhuang.scavengerhunt.MainActivity;
import com.example.yhuang.scavengerhunt.R;

/**
 * Created by yhuang on 10/19/2015.
 */
public class FragmentTransition extends MainActivity {

    private  FragmentManager fm = (FragmentManager) getSupportFragmentManager();

    public void changeToClue() {
        ClueFetch clue_fragment = new ClueFetch();
        transitionToFragment(clue_fragment);
    }

    private void transitionToFragment(Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity, fragment);
        transaction.commit();
    }
}
