package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Nika Doghonadze
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SwitchStatus {

    @JsonProperty("house_id")
    private String houseId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("seconds_left")
    private Long secondsLeft;


    public SwitchStatus() {

    }

    public SwitchStatus(String houseId, String status, Long secondsLeft) {
        this.houseId = houseId;
        this.status = status;
        this.secondsLeft = secondsLeft;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getSecondsLeft() {
        return secondsLeft;
    }

    public void setSecondsLeft(Long secondsLeft) {
        this.secondsLeft = secondsLeft;
    }

    public static SwitchStatus fromGarden(Garden garden) {
        String houseId = garden.getHouseId();
        String status = garden.getSprinkler().isOn() ? "on" : "off";
        Long secondsLeft = garden.getSprinkler().isOn() ? garden.getSprinkler().getSecondsLeft() : null;
        return new SwitchStatus(houseId, status, secondsLeft);
    }
}
