package mitx.bruno.com.tripstuffreminder.data.db;

import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Trip;

/**
 * Created by BPardini on 28/03/2017.
 */

public interface TripRealmContract {

    void onSuccessGetTrips(List<Trip> trips);
    void onErrorGetTrips();

}
