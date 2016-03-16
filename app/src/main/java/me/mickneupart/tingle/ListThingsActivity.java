package me.mickneupart.tingle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.mickneupart.tingle.ListThings.ListThingsFragment;


public class ListThingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_things);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.listActivity_fragVer_container);

        if (fragment == null) {
            fragment = new ListThingsFragment();
            fm.beginTransaction()
                    .add(R.id.listActivity_fragVer_container, fragment)
                    .commit();
        }


    }
}
