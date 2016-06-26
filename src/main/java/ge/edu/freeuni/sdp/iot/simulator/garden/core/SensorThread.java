package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.Garden;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.SoilMoistureSensorRequest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.Random;

/**
 * Created by khrak on 6/26/16.
 */
public class SensorThread extends Thread {

    private static final long FIX_TIME = 1000 * 60;

    private long sleepTime;
    private String serviceUrl;

    public SensorThread(long sleepTimeMillis, String serviceUrl) {
        this.sleepTime = sleepTimeMillis;
        this.serviceUrl = serviceUrl;
    }

    @Override
    public void run() {

        Repository repository = RepositoryFactory.inMemoryRepositoryInstance();
        Random chance = new Random();
        long sleepTime;

        while (true) {
            for (Garden g : repository.getGardens())
                postSensorValue(g);

            sleepTime = this.sleepTime;

            if (chance.nextInt() % 10000 == 0)
                sleepTime = FIX_TIME;

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                break;
            }
        }

    }

    private void postSensorValue(Garden garden) {
        Client client = ClientBuilder.newClient();
        SoilMoistureSensorRequest request = new SoilMoistureSensorRequest(garden.getSoilSensor().getValue());
        client.target(serviceUrl + "/house/" + garden.getHouseId())
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(request));
    }

}
