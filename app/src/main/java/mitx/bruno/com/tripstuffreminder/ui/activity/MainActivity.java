package mitx.bruno.com.tripstuffreminder.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import mitx.bruno.com.tripstuffreminder.R;
import mitx.bruno.com.tripstuffreminder.ui.fragment.AboutFragment;
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

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_about:
                AboutFragment aboutFragment = AboutFragment.newInstance();
                aboutFragment.show(getSupportFragmentManager(), AboutFragment.TAG);
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }
}
