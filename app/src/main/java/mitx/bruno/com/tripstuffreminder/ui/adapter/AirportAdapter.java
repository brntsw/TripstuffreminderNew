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
import mitx.bruno.com.tripstuffreminder.model.Airport;
import mitx.bruno.com.tripstuffreminder.ui.listener.IClickRecyclerViewAirportListener;

/**
 * Created by BPardini on 29/03/2017.
 */

public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.ViewHolder> {

    private List<Airport> mAirports;
    private IClickRecyclerViewAirportListener mListener;

    public AirportAdapter(List<Airport> airports){
        mAirports = airports;
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
        Airport airport = mAirports.get(position);
        holder.root.setOnClickListener(view -> {
            mListener.onItemClicked(airport);
        });
        if(airport != null){
            holder.bind(airport);
        }
    }

    @Override
    public int getItemCount() {
        return mAirports.size();
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
