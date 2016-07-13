import com.jayway.jsonpath.JsonPath;
import ge.edu.freeuni.sdp.iot.simulator.garden.services.GardenService;
import org.apache.log4j.BasicConfigurator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Meko on 13/07/2016.
 */
public class TemperatureTest extends JerseyTest{

    protected Application configure() {
        return new ResourceConfig(GardenService.class);
    }

    @Before
    public void setup(){
        BasicConfigurator.configure();
    }

    private String get_house_id(){
        String json = getJsonText("https://iot-house-registry.herokuapp.com/houses");
        String houseID = JsonPath.read(json, "$[0].RowKey._");
        return houseID;
    }

    @Test
    public void test_temperature_put(){
        try {
            String houseID = get_house_id();
            Entity e = Entity.json("{\"set_temp_c\": 34,\"set_temp_f\": 93 }");
            Response r = target("/houses/" + houseID + "/temperature")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(e);
            JSONObject jsonObject = new JSONObject(r.readEntity(String.class));
            assertEquals(3, jsonObject.length());
            int temp_c = jsonObject.getInt("temp_c");
            int temp_f = jsonObject.getInt("temp_f");
            assertEquals(houseID, jsonObject.getString("house_id"));
            assertEquals(200, r.getStatus());
        }
        catch (Exception ex) {
            assertTrue(false);
        }
    }


    @Test
    public void test_temperature_get(){
        try {
            String houseID = get_house_id();
            String json = getJsonText("https://iot-garden-simulator.herokuapp.com/webapi/houses/"
                    + houseID + "/temperature");
            Response r = target("/houses/" + houseID + "/temperature")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();
            JSONObject jsonObject = new JSONObject(r.readEntity(String.class));
            assertEquals(3, jsonObject.length());
            int temp_c = jsonObject.getInt("temp_c");
            int temp_f = jsonObject.getInt("temp_f");
            assertEquals(houseID, jsonObject.getString("house_id"));
            assertEquals(200, r.getStatus());
        }
        catch (Exception ex) {
            assertTrue(false);
        }
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
