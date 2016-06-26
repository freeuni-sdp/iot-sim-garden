package ge.edu.freeuni.sdp.iot.simulator.garden.model;

/**
 * Created by khrak on 6/26/16.
 */
public class SoilSensor {
    private Double value;

    public SoilSensor(Double value) {
        this.value = value;
    }

    public synchronized void setValue(Double newValue) {
        value = newValue;
    }

    public synchronized  Double getValue() {
        return value;
    }
}
