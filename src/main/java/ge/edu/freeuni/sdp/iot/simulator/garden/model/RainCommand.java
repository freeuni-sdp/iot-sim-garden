package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tornike on 25.06.2016.
 */
public class RainCommand {
    public String getWillRain() {
        return willRain;
    }

    public void setWillRain(String willRain) {
        this.willRain = willRain;
    }

    @JsonProperty(value = "value", required = true)
    private String willRain;

    public RainCommand() {

    }

    public RainCommand(String willRain) {
        this.willRain = willRain;
    }

    public boolean isValid() {
        return (willRain.equals("no") || willRain.equals("yes"));
    }
}
