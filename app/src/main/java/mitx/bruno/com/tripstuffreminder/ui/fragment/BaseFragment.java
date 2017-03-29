package mitx.bruno.com.tripstuffreminder.ui.fragment;

import android.support.v4.app.Fragment;

import butterknife.Unbinder;

/**
 * Created by BPardini on 28/03/2017.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    public void setUnBinder(Unbinder unBinder) {
        mUnbinder = unBinder;
    }

    @Override
    public void onDestroy() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    abstract void setup();

}
