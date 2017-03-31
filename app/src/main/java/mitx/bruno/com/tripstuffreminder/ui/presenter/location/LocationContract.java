package mitx.bruno.com.tripstuffreminder.ui.presenter.location;

import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Location;

/**
 * Created by BPardini on 31/03/2017.
 */

public interface LocationContract {

    interface View{
        void showLoading();
        void hideLoading();
        void successLoadPlaces(List<Location> locations);
        void errorLoadPlaces();
    }

    interface Presenter{
        void getLocations(String address);
    }

}
