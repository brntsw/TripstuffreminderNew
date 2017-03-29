package mitx.bruno.com.tripstuffreminder.ui.presenter.airport;

import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Airport;

/**
 * Created by BPardini on 29/03/2017.
 */

public interface AirportContract {

    interface View{
        void showLoading();
        void hideLoading();
        void onSuccessLoadAirports(List<Airport> airports);
        void onErrorLoadAirports();
    }

    interface Presenter{
        void getAirports();
    }

}
