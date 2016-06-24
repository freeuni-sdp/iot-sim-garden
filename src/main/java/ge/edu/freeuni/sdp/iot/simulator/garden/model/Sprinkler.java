package ge.edu.freeuni.sdp.iot.simulator.garden.model;

/**
 * Created by Nika Doghonadze
 */
public class Sprinkler {
    private long lastOnCommandTime;
    private int timeout;


    public Sprinkler() {
       turnOff();
    }

    public long getSecondsLeft() {
        if (!isOn())
            return 0;

        return timeout - (currTimeSecs() - lastOnCommandTime);
    }

    public boolean isOn() {
        return currTimeSecs() - lastOnCommandTime < timeout;
    }

    public void turnOnFor(int seconds) {
        lastOnCommandTime = currTimeSecs();
        timeout = seconds;
    }

    public void turnOff() {
        timeout = 0;
        lastOnCommandTime = 0;
    }

    private long currTimeSecs() {
        return System.currentTimeMillis()/1000;
    }
}
