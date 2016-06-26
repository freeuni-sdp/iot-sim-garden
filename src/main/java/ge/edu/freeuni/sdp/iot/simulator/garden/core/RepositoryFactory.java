package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class RepositoryFactory {

    private static final long SENSOR_REFRESH_RATE = 100;

    private static Repository instance;
    private static SensorThread sensorThread;

    public static synchronized Repository inMemoryRepositoryInstance() {
        if (instance == null) {
            Map<String, Garden> initialMap = getInitialGardenMap();
            instance = new InMemoryRepository(initialMap);
        }

        return instance;
    }

    private static Map<String, Garden> getInitialGardenMap() {
        HouseRegistry houseRegistry = new HouseRegistryProxy();
        List<HouseData> allHouses = houseRegistry.getAllHouses();
        if (allHouses == null)
            return new HashMap<>();

        Map<String, Garden> res = new HashMap<>();
        for (HouseData house : allHouses) {
            Garden newGarden = initNewGarden(house);
            res.put(house.getHouseId(), newGarden);
        }

        sensorThread = new SensorThread(SENSOR_REFRESH_RATE,
                "https://iot-soil-moisture-sensor.herokuapp.com");
        sensorThread.start();

        return res;
    }

    private static Garden initNewGarden(HouseData house) {
        Sprinkler sprinkler = new Sprinkler();
        Weather weather = new Weather();
        Camera camera = new Camera();
        SoilSensor soilSensor = new SoilSensor(0.0);

        return new Garden(house.getHouseId(), sprinkler, weather, camera, soilSensor);
    }

}
