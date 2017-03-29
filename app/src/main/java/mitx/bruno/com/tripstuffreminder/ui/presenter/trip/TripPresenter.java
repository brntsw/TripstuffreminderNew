package mitx.bruno.com.tripstuffreminder.ui.presenter.trip;

import java.util.List;

import io.realm.Realm;
import mitx.bruno.com.tripstuffreminder.data.db.TripRealm;
import mitx.bruno.com.tripstuffreminder.data.db.TripRealmContract;
import mitx.bruno.com.tripstuffreminder.model.Trip;
import mitx.bruno.com.tripstuffreminder.ui.presenter.trip.TripContract;

/**
 * Created by BPardini on 28/03/2017.
 */

public class TripPresenter implements TripContract.Presenter, TripRealmContract {

    private TripContract.View mTripView;
    private Realm mRealm;
    private TripRealm tripRealm;

    public TripPresenter(TripContract.View tripView, Realm realm){
        mTripView = tripView;
        mRealm = realm;
        tripRealm = new TripRealm(this, mRealm);
    }

    @Override
    public void getTrips() {
        mTripView.showLoading();
        tripRealm.getTrips();
    }

    @Override
    public void onSuccessGetTrips(List<Trip> trips) {
        mTripView.hideLoading();
        mTripView.onSuccessGetTrips(trips);
    }

    @Override
    public void onErrorGetTrips() {
        mTripView.hideLoading();
        mTripView.onErrorGetTrips();
    }
}
