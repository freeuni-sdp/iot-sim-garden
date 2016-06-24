package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.HouseData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nika Doghonadze
 */
public class HouseRegistryProxy implements HouseRegistry {

    @Override
    public List<HouseData> getAllHouses() {
        String respJson = getResponseString("https://iot-house-registry.herokuapp.com/houses/");

        if (respJson == null)
            return null;

        return houseDataFromJsonList(respJson);
    }

    @Override
    public HouseData getHouseData(String houseId) {
        String respJson = getResponseString("https://iot-house-registry.herokuapp.com/houses/" + houseId);

        if (respJson == null)
            return null;

        JSONObject jsonObject = new JSONObject(respJson);
        return HouseData.fromJson(jsonObject);
    }

    private String getResponseString(String url) {
        Client client = ClientBuilder.newClient();
        Response response =
                client.target(url)
                .request(MediaType.APPLICATION_JSON)
                .get();

        int responseStatus = response.getStatus();
        if (responseStatus != Response.Status.OK.getStatusCode())
            return null;

        return response.readEntity(String.class);
    }

    private List<HouseData> houseDataFromJsonList(String respJson) {
        List<HouseData> res = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(respJson);
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                HouseData houseData = HouseData.fromJson(jsonObject);
                res.add(houseData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return res;
    }
}
