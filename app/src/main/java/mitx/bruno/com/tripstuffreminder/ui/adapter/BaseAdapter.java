package mitx.bruno.com.tripstuffreminder.ui.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by BPardini on 04/04/2017.
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    public abstract T getItem(int position);

}
