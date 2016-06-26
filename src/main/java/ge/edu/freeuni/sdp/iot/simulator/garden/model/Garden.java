package ge.edu.freeuni.sdp.iot.simulator.garden.model;

/**
 * Created by Nika Doghonadze
 */
public class Garden {
    private String houseId;
    private Sprinkler sprinkler;
    private Weather weather;
    private Camera camera;

    public Garden() {

    }

    public Garden(String houseId, Sprinkler sprinkler, Weather weather, Camera camera) {
        this.houseId = houseId;
        this.sprinkler = sprinkler;
        this.weather = weather;
        this.camera = camera;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public Sprinkler getSprinkler() {
        return sprinkler;
    }

    public void setSprinkler(Sprinkler sprinkler) {
        this.sprinkler = sprinkler;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
