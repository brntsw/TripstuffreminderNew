package mitx.bruno.com.tripstuffreminder.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.model.Trip;
import mitx.bruno.com.tripstuffreminder.ui.adapter.TripAdapter;
import mitx.bruno.com.tripstuffreminder.ui.listener.IClickRecyclerViewTripItemListener;
import mitx.bruno.com.tripstuffreminder.ui.presenter.trip.TripContract;
import mitx.bruno.com.tripstuffreminder.ui.presenter.trip.TripPresenter;
import mitx.bruno.com.tripstuffreminder.utils.CommonUtils;

/**
 * Created by BPardini on 28/03/2017.
 */

public class TripListFragment extends BaseFragment implements TripContract.View, IClickRecyclerViewTripItemListener {

    public static final String TRIP_TAG = "TripListFragment";

    @BindView(R.id.coordinator_trip_list)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.relative_no_trip)
    RelativeLayout relativeLayoutNoTrip;

    @BindView(R.id.recycler_trips)
    RecyclerView recyclerTrips;

    private ProgressDialog mProgress;
    private Realm mRealm;
    private TripContract.Presenter presenter;

    public static TripListFragment newInstance(){
        return new TripListFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_trip_list, container, false);
        setUnBinder(ButterKnife.bind(this, rootView));

        setup();

        presenter.getTrips();

        return rootView;
    }

    @OnClick(R.id.fab_add)
    public void buttonFabAddClick(){
        AddTripFragment addTripFragment = AddTripFragment.newInstance();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, addTripFragment, AddTripFragment.ADD_TRIP)
                .addToBackStack(TRIP_TAG)
                .commit();
    }

    @Override
    void setup() {
        mRealm = Realm.getDefaultInstance();
        presenter = new TripPresenter(this, mRealm);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerTrips.setLayoutManager(manager);
        recyclerTrips.setAdapter(null);
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgress = CommonUtils.showLoadingDialog(getActivity(), "");
    }

    @Override
    public void hideLoading() {
        if(mProgress != null && mProgress.isShowing()){
            mProgress.cancel();
        }
    }

    @Override
    public void onSuccessGetTrips(List<Trip> trips) {
        if(trips.size() > 0){
            TripAdapter adapter = new TripAdapter(trips);
            adapter.addClickListener(this);
            recyclerTrips.setAdapter(adapter);
        }
        else{
            recyclerTrips.setVisibility(View.GONE);
            relativeLayoutNoTrip.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onErrorGetTrips() {
        CommonUtils.showSnackBar(getActivity(), coordinatorLayout, getString(R.string.error_trips));
    }

    @Override
    public void onItemClicked(int tripId) {
        Bundle bundle = new Bundle();
        bundle.putInt("tripId", tripId);
    }
}
