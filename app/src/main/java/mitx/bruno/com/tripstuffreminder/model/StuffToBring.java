package mitx.bruno.com.tripstuffreminder.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by BPardini on 28/03/2017.
 */

public class StuffToBring extends RealmObject {

    @PrimaryKey
    private int id;
    private int tripId;
    private String stuffName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getStuffName() {
        return stuffName;
    }

    public void setStuffName(String stuffName) {
        this.stuffName = stuffName;
    }
}
