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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.model.Airport;
import mitx.bruno.com.tripstuffreminder.ui.adapter.AirportAdapter;
import mitx.bruno.com.tripstuffreminder.ui.listener.IClickRecyclerViewAirportListener;
import mitx.bruno.com.tripstuffreminder.ui.presenter.airport.AirportContract;
import mitx.bruno.com.tripstuffreminder.ui.presenter.airport.AirportPresenter;
import mitx.bruno.com.tripstuffreminder.utils.CommonUtils;

/**
 * Created by BPardini on 29/03/2017.
 */

public class SelectAirportFragment extends DialogFragment implements AirportContract.View, IClickRecyclerViewAirportListener {

    @BindView(R.id.coordinator_airports)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.recycler_airports)
    RecyclerView recyclerAirports;

    private ProgressDialog mProgress;
    private Unbinder unbinder;
    private AddTripFragment mAddTripFragment;
    private AirportContract.Presenter presenter;

    public static final String TAG = SelectAirportFragment.class.getName();

    public void setAddFragment(AddTripFragment addFragment){
        mAddTripFragment = addFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_select_airport, null, false);

        unbinder = ButterKnife.bind(this, view);

        presenter = new AirportPresenter(this, getActivity().getAssets());
        presenter.getAirports();

        return initiateAlertDialog(view);
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
    public void onSuccessLoadAirports(List<Airport> airports) {
        AirportAdapter adapter = new AirportAdapter(airports);
        adapter.addClickListener(this);

        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerAirports.setLayoutManager(manager);
        recyclerAirports.setAdapter(adapter);
    }

    @Override
    public void onErrorLoadAirports() {
        CommonUtils.showSnackBar(getActivity(), coordinatorLayout, getString(R.string.error_airports));
    }

    @Override
    public void onItemClicked(Airport airport) {
        dismiss();
        mAddTripFragment.onAirportSelected(airport);
    }
}