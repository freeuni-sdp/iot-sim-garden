package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import org.json.JSONObject;

/**
 * Created by Nika Doghonadze
 */
public class HouseData {
    private String houseId;

    public HouseData(String houseId) {
        this.houseId = houseId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public static HouseData fromJson(JSONObject jsonObject) {
        String houseId = jsonObject.getJSONObject("RowKey").getString("_");
        return new HouseData(houseId);
    }
}
