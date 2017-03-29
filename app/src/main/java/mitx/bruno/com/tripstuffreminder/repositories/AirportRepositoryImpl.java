package mitx.bruno.com.tripstuffreminder.repositories;

import android.content.res.AssetManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import mitx.bruno.com.tripstuffreminder.model.Airport;
import rx.Observable;

/**
 * Created by BPardini on 29/03/2017.
 */

public class AirportRepositoryImpl implements AirportRepository {

    private AssetManager mAssetManager;

    public AirportRepositoryImpl(AssetManager assetManager){
        mAssetManager = assetManager;
    }

    @Override
    public Observable<List<Airport>> list() {
        InputStream in = null;

        try{
            try {
                in = mAssetManager.open("airports.json");

                Reader reader = new InputStreamReader(in, Charset.forName("UTF-8"));

                ObjectMapper mapper = new ObjectMapper();
                List<Airport> airports = mapper.readValue(reader, new TypeReference<List<Airport>>(){});

                return Observable.just(airports);
            }
            finally {
                in.close();
            }
        }
        catch (Exception ex){
            return Observable.error(ex);
        }
    }
}
