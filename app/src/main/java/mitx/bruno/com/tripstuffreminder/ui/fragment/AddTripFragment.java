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
import mitx.bruno.com.tripstuffreminder.model.Location;

/**
 * Created by BPardini on 29/03/2017.
 */

public class AddTripFragment extends BaseFragment {

    @BindView(R.id.edit_destination)
    EditText editDestination;

    @BindView(R.id.edit_departure_airport)
    EditText editDepartureAirport;

    @BindView(R.id.edit_destination_airport)
    EditText editDestinationAirport;

    public static final String ADD_TRIP = "AddTripFragment";

    public static AddTripFragment newInstance(){
        return new AddTripFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState){
        if(container != null){
            container.removeAllViews();
        }

        View rootView = inflater.inflate(R.layout.fragment_add_trip, container, false);
        setUnBinder(ButterKnife.bind(this, rootView));

        setup();

        return rootView;
    }

    @Override
    void setup() {
        editDepartureAirport.setOnClickListener(view -> {
            Bundle args = new Bundle();
            args.putString(Airport.BUNDLE_AIRPORT_TYPE, Airport.DEPARTURE_AIRPORT);

            SelectAirportFragment selectAirportFragment = new SelectAirportFragment();
            selectAirportFragment.setArguments(args);
            selectAirportFragment.setAddFragment(this);
            selectAirportFragment.show(getFragmentManager(), SelectAirportFragment.TAG);
        });

        editDestinationAirport.setOnClickListener(view -> {
            Bundle args = new Bundle();
            args.putString(Airport.BUNDLE_AIRPORT_TYPE, Airport.DESTINATION_AIRPORT);

            SelectAirportFragment selectAirportFragment = new SelectAirportFragment();
            selectAirportFragment.setArguments(args);
            selectAirportFragment.setAddFragment(this);
            selectAirportFragment.show(getFragmentManager(), SelectAirportFragment.TAG);
        });

        editDestination.setOnClickListener(view -> {
            SelectLocationFragment selectLocationFragment = new SelectLocationFragment();
            selectLocationFragment.setAddFragment(this);
            selectLocationFragment.show(getFragmentManager(), SelectLocationFragment.TAG);
        });
    }

    public void onDepartureAirportSelected(Airport airport){
        editDepartureAirport.setText(airport.getName());
    }

    public void onDestinationAirportSelected(Airport airport){
        editDestinationAirport.setText(airport.getName());
    }

    public void onLocationSelected(Location location){ editDestination.setText(location.getName()); }
}
