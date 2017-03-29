package mitx.bruno.com.tripstuffreminder.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.ui.fragment.TripListFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnBinder(ButterKnife.bind(this));

        if (findViewById(R.id.fragment_container) != null) {
            TripListFragment tripListFragment = TripListFragment.newInstance();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, tripListFragment).commit();
        }

        setup();
    }

    @Override
    void setup() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
    }
}
