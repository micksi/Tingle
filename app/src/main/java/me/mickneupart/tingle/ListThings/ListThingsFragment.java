package me.mickneupart.tingle.ListThings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.mickneupart.tingle.R;
import me.mickneupart.tingle.ThingsDB.ThingsDB;

/**
 * Created by mickneupart on 24/02/16.
 */
public class ListThingsFragment extends Fragment{

    private ThingsDB mThingsDB;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLlm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_list_things, container, false);

        mThingsDB = ThingsDB.get(this.getContext());

        mRecyclerView = (RecyclerView) v.findViewById(R.id.fragListThings_rv);
        mLlm = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLlm);

        ListThingsAdapter ltAdapter = new ListThingsAdapter(mThingsDB);
        mRecyclerView.setAdapter(ltAdapter);

        ItemTouchHelper.Callback callback = new ListThingsTouchHelper(ltAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);

        return v;
    }

    public void updateUI(){
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }
}
