package mitx.bruno.com.tripstuffreminder.repositories;

import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Airport;
import rx.Observable;

/**
 * Created by BPardini on 29/03/2017.
 */

public interface AirportRepository {

    Observable<List<Airport>> list();

}
