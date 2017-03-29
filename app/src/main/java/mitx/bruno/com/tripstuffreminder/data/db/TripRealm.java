package mitx.bruno.com.tripstuffreminder.data.db;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import mitx.bruno.com.tripstuffreminder.model.Trip;

/**
 * Created by BPardini on 28/03/2017.
 */

public class TripRealm {

    private TripRealmContract mContract;
    private Realm mRealm;
    private List<Trip> trips = new ArrayList<>();

    public TripRealm(TripRealmContract contract, Realm realm){
        mContract = contract;
        mRealm = realm;
    }

    public void getTrips(){
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Trip> realmTrips = realm.where(Trip.class).findAll();

                for(Trip trip : realmTrips){
                    Trip tripTemp = realm.copyFromRealm(trip);
                    trips.add(tripTemp);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mContract.onSuccessGetTrips(trips);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                mContract.onErrorGetTrips();
            }
        });
    }
}
