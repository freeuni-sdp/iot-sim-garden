package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tornike on 25.06.2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperatureStatus {

    @JsonProperty("house_id")
    private String houseId;

    @JsonProperty("temp_c")
    private int tempC;

    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
    }

    public int getTempF() {
        return tempF;
    }

    public void setTempF(int tempF) {
        this.tempF = tempF;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @JsonProperty("temp_f")
    private int tempF;

    public TemperatureStatus() {

    }

    public TemperatureStatus(String houseId, int tempC, int tempF) {
        this.houseId = houseId;
        this.tempC = tempC;
        this.tempF = tempF;
    }

    public static TemperatureStatus fromGarden(Garden garden) {
        String houseId = garden.getHouseId();
        int tempC = garden.getWeather().getTempC();
        int tempF = garden.getWeather().getTempF();
        return new TemperatureStatus(houseId, tempC, tempF);
    }
}
