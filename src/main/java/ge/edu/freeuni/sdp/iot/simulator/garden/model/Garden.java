package ge.edu.freeuni.sdp.iot.simulator.garden.model;

/**
 * Created by Nika Doghonadze
 */
public class Garden {
    private String houseId;
    private Sprinkler sprinkler;

    public Garden() {

    }

    public Garden(String houseId, Sprinkler sprinkler) {
        this.houseId = houseId;
        this.sprinkler = sprinkler;
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
}
