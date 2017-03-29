package mitx.bruno.com.tripstuffreminder.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by BPardini on 28/03/2017.
 */

public class Trip extends RealmObject {

    @PrimaryKey
    private int id;
    private int departureAirportId;
    private String city;
    private String state;
    private String departureDate;
    private String arrivalDate;
    private RealmList<StuffToBring> stuffToBringList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(int departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public RealmList<StuffToBring> getStuffToBringList() {
        return stuffToBringList;
    }

    public void setStuffToBringList(RealmList<StuffToBring> stuffToBringList) {
        this.stuffToBringList = stuffToBringList;
    }
}
