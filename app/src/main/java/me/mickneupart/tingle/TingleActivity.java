package me.mickneupart.tingle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.mickneupart.tingle.ListThings.ListThingsFragment;
import me.mickneupart.tingle.Tingle.TingleFragment;

public class TingleActivity extends AppCompatActivity implements TingleFragment.TingleObserver {

    private FragmentManager fm;

    private Fragment tingleFragment;
    private Fragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tingle);

        fm = getSupportFragmentManager();

        tingleFragment = fm.findFragmentById(R.id.tingleActivity_frag_tingle);
        listFragment = fm.findFragmentById(R.id.tingleActivity_frag_thingsList);

        if (tingleFragment == null) {
            tingleFragment = new TingleFragment();
            fm.beginTransaction()
                    .add(R.id.tingleActivity_frag_tingle, tingleFragment)
                    .commit();
        }

        if (getResources().getConfiguration().orientation == 2){
            if (listFragment == null) {
                listFragment = new ListThingsFragment();
                fm.beginTransaction()
                        .add(R.id.tingleActivity_frag_thingsList, listFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onThingAdded(){
        if (getResources().getConfiguration().orientation == 2){
            ((ListThingsFragment)listFragment).updateUI();
        }
    }
}
