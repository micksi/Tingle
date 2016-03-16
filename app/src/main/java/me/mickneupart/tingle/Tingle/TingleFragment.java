package me.mickneupart.tingle.Tingle;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.util.Iterator;

import me.mickneupart.tingle.ListThingsActivity;
import me.mickneupart.tingle.R;
import me.mickneupart.tingle.ThingsDB.Thing;
import me.mickneupart.tingle.ThingsDB.ThingsDB;

public class TingleFragment extends Fragment {

    private Button addThing;
    private Button searchThing;
    private Button viewAll;

    private TextView lastAdded;
    private TextView newWhat, newWhere;

    private ThingsDB mThingsDB;

    private TingleObserver mCallback;

    public interface TingleObserver {
        public void onThingAdded();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tingle, container, false);

        setup(v);
        updateUI();
        return v;
    }

    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (TingleObserver) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    private void setup(View view){
        mThingsDB = ThingsDB.get(this.getContext());

        lastAdded = (TextView) view.findViewById(R.id.tingleActivity_textLastThing);
        newWhat = (TextView) view.findViewById(R.id.tingleActivity_textWhat);
        newWhere = (TextView) view.findViewById(R.id.tingleActivity_textWhere);

        searchThing = (Button) view.findViewById(R.id.tingleActivity_btnSearch);
        addThing = (Button) view.findViewById(R.id.tingleActivity_btnAdd);
        viewAll = (Button) view.findViewById(R.id.tingleActivity_btnViewAll);

        newWhere.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    addThing.callOnClick();

                    return true;
                }
                return false;
            }
        });

        addThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((newWhat.getText().length() > 0) && (newWhere.getText().length() > 0)){
                    mThingsDB.addThing(
                            new Thing(newWhat.getText().toString(),
                                    newWhere.getText().toString()));
                    newWhat.setText("");
                    newWhere.setText("");
                    updateUI();
                    mCallback.onThingAdded();
                }
            }
        });


        searchThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: should do the search in a separate thread instead of the UI-thread
                for (Iterator<Thing> i = mThingsDB.getThingsDB().iterator() ; i.hasNext();) {
                    Thing t = i.next();
                    if( newWhat.getText().toString().trim().toLowerCase().
                            equals(t.getWhat().trim().toLowerCase())) {
                        String s = t.getWhat() + " can be found here: " + t.getWhere();
                        Toast.makeText(TingleFragment.this.getActivity(), s, Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
        });

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TingleFragment.this.getActivity(), ListThingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateUI(){
        int s = mThingsDB.size();
        if (s > 0){
            lastAdded.setText(mThingsDB.get(s - 1).toString());
        }
    }

}
