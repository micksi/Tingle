package me.mickneupart.tingle.ThingsDB;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mickneupart on 11/02/16.
 */
public class ThingsDB {

    private static ThingsDB sThingsDB;
    //fake database
    private List<Thing> mThingsDB;

    public static ThingsDB get(Context context) {
        if (sThingsDB == null) {
            sThingsDB= new ThingsDB(context);
        }
        return sThingsDB;
    }

    public List<Thing> getThingsDB() {
        return mThingsDB;
    }

    public void addThing(Thing thing) {
        mThingsDB.add(thing);
    }

    public int size() {
        return mThingsDB.size();
    }

    public Thing get(int i){
        return mThingsDB.get(i);
    }

    // Fill database for testing purposes
    private ThingsDB(Context context) {
        mThingsDB= new ArrayList<Thing>();
        mThingsDB.add(new Thing("Kiwi", "Føtex"));
        mThingsDB.add(new Thing("Banana", "Kiwi"));
        mThingsDB.add(new Thing("Orange", "Netto"));
        mThingsDB.add(new Thing("Apple", "Irma"));
        mThingsDB.add(new Thing("Pineapple", "Bilka"));
        mThingsDB.add(new Thing("Mango", "Super Brugsen"));
        mThingsDB.add(new Thing("Pear", "Rema 1000"));
    }
}
