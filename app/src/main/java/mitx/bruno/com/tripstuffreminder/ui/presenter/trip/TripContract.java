package mitx.bruno.com.tripstuffreminder.ui.presenter.trip;

import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Trip;

/**
 * Created by BPardini on 28/03/2017.
 */

public interface TripContract {

    interface View{
        void showLoading();
        void hideLoading();
        void onSuccessGetTrips(List<Trip> trips);
        void onErrorGetTrips();
    }

    interface Presenter{
        void getTrips();
    }

}
