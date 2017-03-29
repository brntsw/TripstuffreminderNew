package mitx.bruno.com.tripstuffreminder.ui.presenter.airport;

import android.content.res.AssetManager;
import android.util.Log;

import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Airport;
import mitx.bruno.com.tripstuffreminder.repositories.AirportRepository;
import mitx.bruno.com.tripstuffreminder.repositories.AirportRepositoryImpl;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by BPardini on 29/03/2017.
 */

public class AirportPresenter implements AirportContract.Presenter {

    private AirportContract.View mAirportView;
    private AssetManager mAssetManager;
    private AirportRepository airportRepository;

    public AirportPresenter(AirportContract.View airportView, AssetManager assetManager){
        mAirportView = airportView;
        mAssetManager = assetManager;
        airportRepository = new AirportRepositoryImpl(mAssetManager);
    }

    @Override
    public void getAirports() {
        mAirportView.showLoading();

        Observable<List<Airport>> observable = airportRepository.list();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    mAirportView.hideLoading();
                    mAirportView.onErrorLoadAirports();
                });

        observable.subscribe(new Subscriber<List<Airport>>() {
            @Override
            public void onCompleted() {
                Log.d("onCompleted", "Completed loading airports");
            }

            @Override
            public void onError(Throwable e) {
                mAirportView.hideLoading();
                mAirportView.onErrorLoadAirports();
            }

            @Override
            public void onNext(List<Airport> airports) {
                mAirportView.hideLoading();
                mAirportView.onSuccessLoadAirports(airports);
            }
        });
    }
}
