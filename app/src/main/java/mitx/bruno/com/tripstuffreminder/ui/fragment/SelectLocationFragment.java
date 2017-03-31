package mitx.bruno.com.tripstuffreminder.ui.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.model.Location;
import mitx.bruno.com.tripstuffreminder.ui.adapter.LocationAdapter;
import mitx.bruno.com.tripstuffreminder.ui.listener.IClickRecyclerViewLocationListener;
import mitx.bruno.com.tripstuffreminder.ui.presenter.location.LocationContract;
import mitx.bruno.com.tripstuffreminder.ui.presenter.location.LocationPresenter;
import mitx.bruno.com.tripstuffreminder.utils.CommonUtils;

/**
 * Created by BPardini on 31/03/2017.
 */

public class SelectLocationFragment extends DialogFragment implements LocationContract.View, IClickRecyclerViewLocationListener {

    @BindView(R.id.coordinator_locations)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.input_search)
    EditText editSearch;

    @BindView(R.id.recycler_locations)
    RecyclerView recyclerLocations;

    private ProgressDialog mProgress;
    private Unbinder unbinder;
    private AddTripFragment mAddTripFragment;
    private LocationContract.Presenter presenter;

    public static final String TAG = SelectAirportFragment.class.getName();

    public void setAddFragment(AddTripFragment addFragment){
        mAddTripFragment = addFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_select_location, null, false);

        unbinder = ButterKnife.bind(this, view);

        presenter = new LocationPresenter(getActivity(), this);

        return initiateAlertDialog(view);
    }

    @OnClick(R.id.bt_search)
    public void buttonSearchClick(){
        String strLocation = editSearch.getText().toString();

        if(!"".equals(strLocation)){
            presenter.getLocations(strLocation);
        }
        else{
            CommonUtils.showSnackBar(getActivity(), coordinatorLayout, getString(R.string.hint_location_name));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private AlertDialog initiateAlertDialog(View view){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog_Alert);
        builder.setView(view)
                .setNegativeButton(R.string.ok, (dialog, id) -> dialog.dismiss());

        return builder.create();
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
    public void successLoadPlaces(List<Location> locations) {
        LocationAdapter adapter = new LocationAdapter(locations);
        adapter.addClickListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerLocations.setLayoutManager(manager);
        recyclerLocations.setAdapter(adapter);
    }

    @Override
    public void errorLoadPlaces() {
        CommonUtils.showSnackBar(getActivity(), coordinatorLayout, getString(R.string.error_locations));
    }

    @Override
    public void onItemClicked(Location location) {
        dismiss();
        mAddTripFragment.onLocationSelected(location);
    }
}
