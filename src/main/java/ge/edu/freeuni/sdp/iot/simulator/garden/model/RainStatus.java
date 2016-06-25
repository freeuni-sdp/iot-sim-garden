package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tornike on 25.06.2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RainStatus {

    @JsonProperty("house_id")
    private String houseId;

    @JsonProperty("value")
    private String willRain;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getWillRain() {
        return willRain;
    }

    public void setWillRain(String willRain) {
        this.willRain = willRain;
    }

    public RainStatus() {

    }

    public RainStatus(String houseId, String willRain) {
        this.houseId = houseId;
        this.willRain = willRain;
    }

    public static RainStatus fromGarden(Garden garden) {
        String houseId = garden.getHouseId();
        String willRain = garden.getWeather().getWillRain();
        return new RainStatus(houseId, willRain);
    }
}
