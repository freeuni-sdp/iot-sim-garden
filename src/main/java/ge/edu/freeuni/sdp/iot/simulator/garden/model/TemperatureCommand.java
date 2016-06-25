package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tornike on 25.06.2016.
 */
public class TemperatureCommand {
    @JsonProperty(value = "set_temp_c", required = true)
    private int tempC;
    @JsonProperty(value = "set_temp_f", required = true)
    private int tempF;

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

    public TemperatureCommand() {

    }

    public TemperatureCommand(int tempC, int tempF) {
        this.tempC = tempC;
        this.tempF = tempF;
    }

    public boolean isValid() {
        return Math.abs(tempC * 1.8 + 32 - tempF) <= 1;
    }
}
