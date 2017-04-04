package mitx.bruno.com.tripstuffreminder.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mitx.bruno.com.tripstuffreminder.R;

/**
 * Created by BPardini on 04/04/2017.
 */

public class AboutFragment extends DialogFragment {

    private Unbinder unbinder;

    public static final String TAG = AboutFragment.class.getName();

    public static AboutFragment newInstance(){
        return new AboutFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.fragment_about, null, false);
        unbinder = ButterKnife.bind(this, view);

        return initializeAlertDialog(view);
    }

    private AlertDialog initializeAlertDialog(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppTheme_Dialog_Alert);
        builder.setView(view)
                .setNegativeButton(R.string.ok, (dialog, id) -> dialog.dismiss());

        return builder.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
