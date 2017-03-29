package mitx.bruno.com.tripstuffreminder.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.model.Airport;

/**
 * Created by BPardini on 29/03/2017.
 */

public class AddTripFragment extends BaseFragment {

    @BindView(R.id.edit_destination)
    EditText editDestination;

    public static final String ADD_TRIP = "AddTripFragment";

    public static AddTripFragment newInstance(){
        return new AddTripFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        View rootView = inflater.inflate(R.layout.fragment_add_trip, container, false);
        setUnBinder(ButterKnife.bind(this, rootView));

        setup();

        return rootView;
    }

    @Override
    void setup() {
        editDestination.setOnClickListener(view -> {
            SelectAirportFragment selectAirportFragment = new SelectAirportFragment();
            selectAirportFragment.setAddFragment(this);
            selectAirportFragment.show(getFragmentManager(), SelectAirportFragment.TAG);
        });
    }

    public void onAirportSelected(Airport airport){
        editDestination.setText(airport.getName());
    }
}
