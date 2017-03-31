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
import mitx.bruno.com.tripstuffreminder.model.Location;
import mitx.bruno.com.tripstuffreminder.ui.listener.IClickRecyclerViewLocationListener;

/**
 * Created by BPardini on 31/03/2017.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

    private List<Location> mLocations;
    private IClickRecyclerViewLocationListener mListener;

    public LocationAdapter(List<Location> locations){
        mLocations = locations;
    }

    public void addClickListener(IClickRecyclerViewLocationListener listener){
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false);
        return new LocationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Location location = mLocations.get(position);
        holder.root.setOnClickListener(view -> {
            mListener.onItemClicked(location);
        });
        if(location != null){
            holder.bind(location);
        }
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.root)
        LinearLayout root;

        @BindView(R.id.tv_location)
        TextView tvLocation;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Location location){
            tvLocation.setText(location.getName());
        }
    }

}
