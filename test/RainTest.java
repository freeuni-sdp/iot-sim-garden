import ge.edu.freeuni.sdp.iot.simulator.garden.services.GardenService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import com.jayway.jsonpath.JsonPath;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Tornike on 12.07.2016.
 */
public class RainTest extends JerseyTest {

    protected Application configure() {
        return new ResourceConfig(GardenService.class);
    }

    @Test
    public void test_rain(){
        try {
            String json = getJsonText("https://iot-house-registry.herokuapp.com/houses");
            String houseID = JsonPath.read(json, "$[0].RowKey._");
            json = getJsonText("https://iot-garden-simulator.herokuapp.com/webapi/houses/" + houseID + "/rain");
            int status = target("/houses/" + houseID + "/rain").request().get().getStatus();
            assertEquals(200, status);
            String value = JsonPath.read(json, "$.value");
            String house_id = JsonPath.read(json, "$.house_id");
            Map a = JsonPath.read(json, "$");
            assertEquals(house_id, houseID);
            assertTrue(value.equals("no") || value.equals("yes"));
            assertEquals(a.size(), 2);

            Entity e = Entity.json("{\"value\": \"" + value + "\"}");
            Response r = target("/houses/" + houseID + "/rain")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(e);
            JSONObject jsonObject = new JSONObject(r.readEntity(String.class));
            assertEquals(value, jsonObject.getString("value"));
            assertEquals(houseID, jsonObject.getString("house_id"));
            assertEquals(2, jsonObject.length());
            assertEquals(200, r.getStatus());

            String differentValue = (value.equals("no") ? "yes" : "no");

            e = Entity.json("{\"value\": \"" + differentValue + "\"}");
            r = target("/houses/" + houseID + "/rain")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(e);
            jsonObject = new JSONObject(r.readEntity(String.class));
            assertEquals(differentValue, jsonObject.getString("value"));
            assertEquals(houseID, jsonObject.getString("house_id"));
            assertEquals(2, jsonObject.length());
            assertEquals(200, r.getStatus());

            e = Entity.json("{\"value\": \"" + value + "\"}");
            r = target("/houses/" + houseID + "/rain")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(e);
            jsonObject = new JSONObject(r.readEntity(String.class));
            assertEquals(value, jsonObject.getString("value"));
            assertEquals(houseID, jsonObject.getString("house_id"));
            assertEquals(2, jsonObject.length());
            assertEquals(200, r.getStatus());
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }

    @Test
    public void bad_request_test(){
        int status = target("/houses/jumberiko/rain").request().get().getStatus();
        assertEquals(404, status);
        status = target("/houses/tengizi/rain").request().put(Entity.json("{\"value\":\"yes\"}")).getStatus();
        assertEquals(404, status);
    }

    private static String getJsonText(String address){
        String json = "";
        try {
            URL url = new URL(address);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String part;
            while (null != (part = br.readLine())) {
                json += part;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return json;
    }
}