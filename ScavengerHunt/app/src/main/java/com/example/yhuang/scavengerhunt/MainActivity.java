package com.example.yhuang.scavengerhunt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yhuang.scavengerhunt.Database.CallbackInterface;
import com.example.yhuang.scavengerhunt.Database.ClueDBConnection;
import com.example.yhuang.scavengerhunt.Database.ClueRow;
import com.example.yhuang.scavengerhunt.Fragments.ClueFetch;
import com.example.yhuang.scavengerhunt.Fragments.GpsDetection;
import com.example.yhuang.scavengerhunt.Fragments.ImageUpload;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static Map<Integer,ClueRow.Row> locationMap; //Map to access all info about a clue

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClueDBConnection clueLocations = new ClueDBConnection(this);
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
        changeToClue();
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

    //Change to a clue fragment using transaction
    public void changeToClue() {
        ClueFetch clue_fragment = new ClueFetch();
        transitionToFragment(clue_fragment);
    }

    /*Change to a GPS fragment
    public void changeToGps() {
        GpsDetection gps_fragment = new GpsDetection();
        transitionToFragment(gps_fragment);
    }*/

    public void changeToImage() {
        ImageUpload image_fragment = new ImageUpload();
        transitionToFragment(image_fragment);
    }

    public void transitionToFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_activity, fragment);
        transaction.commit();
    }
}
