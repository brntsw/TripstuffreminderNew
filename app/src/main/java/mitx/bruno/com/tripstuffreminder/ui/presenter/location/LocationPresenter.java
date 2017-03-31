package mitx.bruno.com.tripstuffreminder.ui.presenter.location;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Location;

/**
 * Created by BPardini on 31/03/2017.
 */

public class LocationPresenter implements LocationContract.Presenter {

    private Activity mActivity;
    private LocationContract.View mLocationView;

    public LocationPresenter(Activity activity, LocationContract.View locationView){
        mActivity = activity;
        mLocationView = locationView;
    }

    public void getLocations(String address){
        mLocationView.showLoading();
        List<Location> listLocations = new ArrayList<>();

        Geocoder geo = new Geocoder(mActivity);

        try{
            List<Address> addresses = geo.getFromLocationName(address, 50);

            for(Address addressGeo : addresses){
                Location location = new Location();
                location.setName(addressGeo.getFeatureName());
                location.setLatitude(addressGeo.getLatitude());
                location.setLongitude(addressGeo.getLongitude());

                listLocations.add(location);
            }
        }
        catch (IOException e){
            Log.e("getLocations", e.getMessage());
            mLocationView.hideLoading();
            mLocationView.errorLoadPlaces();
        }
        finally {
            mLocationView.hideLoading();
            mLocationView.successLoadPlaces(listLocations);
        }
    }

}
