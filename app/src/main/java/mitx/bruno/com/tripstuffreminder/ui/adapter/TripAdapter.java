package mitx.bruno.com.tripstuffreminder.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.model.Trip;
import mitx.bruno.com.tripstuffreminder.ui.listener.IClickRecyclerViewTripItemListener;

/**
 * Created by BPardini on 28/03/2017.
 */

public class TripAdapter extends BaseAdapter {

    private List<Trip> mTrips;
    private IClickRecyclerViewTripItemListener mListener;

    public TripAdapter(List<Trip> trips){
        mTrips = trips;
    }

    public void addClickListener(IClickRecyclerViewTripItemListener listener){
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;

        Trip trip = mTrips.get(position);
        viewHolder.root.setOnClickListener((view) -> mListener.onItemClicked(trip.getId()));
        if(trip != null){
            viewHolder.bind(trip);
        }
    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    @Override
    public Object getItem(int position) {
        return mTrips.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.root)
        LinearLayout root;

        @BindView(R.id.tv_city)
        TextView tvCity;

        @BindView(R.id.tv_state)
        TextView tvState;

        @BindView(R.id.tv_departure)
        TextView tvDeparture;

        @BindView(R.id.tv_arrival)
        TextView tvArrival;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Trip trip){
            tvCity.setText(trip.getCity());
            tvState.setText(trip.getState());
            tvDeparture.setText(trip.getDepartureDate());
            tvArrival.setText(trip.getArrivalDate());
        }
    }

}
