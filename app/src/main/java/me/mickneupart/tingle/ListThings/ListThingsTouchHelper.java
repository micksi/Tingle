package me.mickneupart.tingle.ListThings;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by mickneupart on 27/02/16.
 */
public class ListThingsTouchHelper extends ItemTouchHelper.SimpleCallback {
    private ListThingsAdapter mListThingsAdapter;

    public ListThingsTouchHelper (ListThingsAdapter listThingsAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT);
        mListThingsAdapter = listThingsAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //TODO: Not implemented here
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction == ItemTouchHelper.RIGHT)
            mListThingsAdapter.removeItem(viewHolder.getAdapterPosition());
    }
}
