package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.Garden;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.SoilMoistureSensorRequest;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.SoilSensor;

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

    private static final double MAX_MOISTURE = 100;
    private static final double MIN_MOISTURE = 0;

    private static final double INCREASE_RATE = 0.005;
    private static final double DECREASE_RATE = 0.002;

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
            for (Garden g : repository.getGardens()) {
                SoilSensor sensor = g.getSoilSensor();
                double oldValue = sensor.getValue();
                if (g.getSprinkler().isOn())
                    sensor.setValue(oldValue + (MAX_MOISTURE - oldValue) * INCREASE_RATE);
                else
                    sensor.setValue(oldValue + (MIN_MOISTURE - oldValue) * DECREASE_RATE);

                postSensorValue(g);
            }

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
