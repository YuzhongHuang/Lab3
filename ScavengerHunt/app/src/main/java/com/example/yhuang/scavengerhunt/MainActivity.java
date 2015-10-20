package com.example.yhuang.scavengerhunt;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yhuang.scavengerhunt.Fragments.ClueFetch;
import com.example.yhuang.scavengerhunt.Database.CallbackInterface;
import com.example.yhuang.scavengerhunt.Database.ClueDBConnection;
import com.example.yhuang.scavengerhunt.Database.ClueRow;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static Map<Integer,ClueRow.Row> locationMap;
    public FragmentManager fm = (FragmentManager) getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //begin fragment transaction to ClueFetch
        changeToClue();

        //Setup database connection
        ClueDBConnection clueLocations = new ClueDBConnection(this);

        // this callback function needs to be moved out to another class
        clueLocations.getLocations(new CallbackInterface() {
            @Override
            public void resultsCallback(Map<Integer, ClueRow.Row> locationArray) {
                locationMap = locationArray;
                /*
                //Access each value using the following format
                for (Map.Entry<Integer, ClueRow.Row> entry : locationMap.entrySet()) {
                    Integer key = entry.getKey();
                    ClueRow.Row row  = entry.getValue();
                }*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
