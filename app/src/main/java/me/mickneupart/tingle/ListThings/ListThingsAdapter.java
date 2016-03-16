package me.mickneupart.tingle.ListThings;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import me.mickneupart.tingle.R;
import me.mickneupart.tingle.ThingsDB.ThingsDB;

/**
 * Created by mickneupart on 27/02/16.
 */
public class ListThingsAdapter extends RecyclerView.Adapter<ListThingsAdapter.ThingViewHolder> {

    public static class ThingViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTextInfo;
        Button mBtnDelete;

        public ThingViewHolder (View v) {
            super(v);
            mCardView = (CardView) v.findViewById(R.id.cvThing);
            mTextInfo = (TextView) v.findViewById(R.id.cvThing_textInfo);
            mBtnDelete = (Button) v.findViewById(R.id.cvThing_btnDelete);
        }


    }

    ThingsDB mThingsDB;

    public ListThingsAdapter(ThingsDB thingDB) {
        mThingsDB = thingDB;
    }

    @Override
    public ThingViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_thing, viewGroup, false);
        ThingViewHolder tvh = new ThingViewHolder(v);


        return tvh;
    }

    @Override
    public void onBindViewHolder(final ThingViewHolder thingViewHolder, int i) {
        thingViewHolder.mTextInfo.setText(mThingsDB.get(i).toString());
        thingViewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removeItem(thingViewHolder.getAdapterPosition());
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }

    public int getItemCount() {
        return mThingsDB.size();
    }

    public void removeItem(int pos) {
        mThingsDB.getThingsDB().remove(pos);
        ListThingsAdapter.this.notifyDataSetChanged();
    }
}
