package mitx.bruno.com.tripstuffreminder.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.model.Airport;
import mitx.bruno.com.tripstuffreminder.ui.listener.IClickRecyclerViewAirportListener;

/**
 * Created by BPardini on 29/03/2017.
 */

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.ViewHolder> implements Filterable {

    private List<Airport> mAirports;
    private List<Airport> mAirportsAux;
    private IClickRecyclerViewAirportListener mListener;

    public AirportAdapter(List<Airport> airports){
        mAirports = airports;
        mAirportsAux = airports;
    }

    public void addClickListener(IClickRecyclerViewAirportListener listener){
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airport, parent, false);
        return new AirportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Airport airport = mAirportsAux.get(position);
        holder.root.setOnClickListener(view -> {
            mListener.onItemClicked(airport);
        });
        if(airport != null){
            holder.bind(airport);
        }
    }

    @Override
    public int getItemCount() {
        return mAirportsAux.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count != 0) {
                    mAirportsAux = (List<Airport>) results.values;
                    notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                List<Airport> filteredArray = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = mAirports;
                    results.count = mAirports.size();
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (Airport airport : mAirports) {
                        String airportName = airport.getName();
                        if (airportName != null && airportName.toLowerCase().contains(constraint.toString())) {
                            filteredArray.add(airport);
                        }
                    }

                    results.count = filteredArray.size();
                    results.values = filteredArray;
                }
                return results;
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.root)
        LinearLayout root;

        @BindView(R.id.tv_airport)
        TextView tvAirport;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Airport airport){
            tvAirport.setText(airport.getName());
        }
    }

}
