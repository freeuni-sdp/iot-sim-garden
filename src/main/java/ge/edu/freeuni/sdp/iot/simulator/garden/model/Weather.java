package ge.edu.freeuni.sdp.iot.simulator.garden.model;

/**
 * Created by Tornike on 25.06.2016.
 */
public class Weather {
    private long tempUpdateTime; // time when temperature updated in milliseconds
    private int tempC, tempF; // temperature in celsius and fahrenheit
    private long rainUpdateTime; // time when rain chance updated in milliseconds
    private String willRain;    // time when rain chance updated. temperature in celsius and fahrenheit

    public Weather(){
        tempUpdateTime = 0;
        rainUpdateTime = 0;
        setTempC(0);
        willRain = "no";
    }

    public long getTempUpdateTime() {
        return tempUpdateTime;
    }

    public void setTempUpdateTime(long tempUpdateTime) {
        this.tempUpdateTime = tempUpdateTime;
    }

    public int getTempC() {
        return tempC;
    }

    public void setTempC(int tempC) {
        this.tempC = tempC;
        this.tempUpdateTime = System.currentTimeMillis();
    }

    public int getTempF() {
        return tempF;
    }

    public void setTempF(int tempF) {
        this.tempF = tempF;
        this.tempUpdateTime = System.currentTimeMillis();
    }

    public long getRainUpdateTime() {
        return rainUpdateTime;
    }

    public void setRainUpdateTime(long rainUpdateTime) {
        this.rainUpdateTime = rainUpdateTime;
    }

    public String getWillRain() {
        return willRain;
    }

    public void setWillRain(String willRain) {
        this.willRain = willRain;
        this.rainUpdateTime = System.currentTimeMillis();
    }

    private long minutes_passed(long milliseconds){ // minutes passed after milliseconds
        return System.currentTimeMillis() - milliseconds;
    }
}
