package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

/**
 * Created by nika on 6/26/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoilMoistureSensorRequest {

    @JsonProperty("sensorValue")
    private Double sensorValue;

    public SoilMoistureSensorRequest(Double sensorValue) {
        this.sensorValue = sensorValue;
    }

    public Double getSensorValue() {
        return sensorValue;
    }

}
