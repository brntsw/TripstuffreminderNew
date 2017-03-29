package mitx.bruno.com.tripstuffreminder.ui.activity;

import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by BPardini on 28/03/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    public void setUnBinder(Unbinder unBinder) {
        mUnbinder = unBinder;
    }

    @Override
    protected void onDestroy() {

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }

        super.onDestroy();
    }

    abstract void setup();

}
