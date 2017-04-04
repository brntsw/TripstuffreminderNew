package mitx.bruno.com.tripstuffreminder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by BPardini on 28/03/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport {

    public static final String BUNDLE_AIRPORT_TYPE = "airport_type";
    public static final String DEPARTURE_AIRPORT = "departure";
    public static final String DESTINATION_AIRPORT = "destination";

    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("iso")
    private String countryIso;
    @JsonProperty("continent")
    private String continent;
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lon")
    private String longitude;
    @JsonProperty("size")
    private String size;
    @JsonProperty("status")
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
