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

public class LocationAdapter extends BaseAdapter {

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;

        Location location = mLocations.get(position);
        viewHolder.root.setOnClickListener(view -> mListener.onItemClicked(location));
        if(location != null){
            viewHolder.bind(location);
        }
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    @Override
    public Object getItem(int position) {
        return mLocations.get(position);
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
