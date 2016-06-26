package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class RepositoryFactory {
    private static Repository instance;

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

        return res;
    }

    private static Garden initNewGarden(HouseData house) {
        Sprinkler sprinkler = new Sprinkler();
        Weather weather = new Weather();
        Camera camera = new Camera();
        return new Garden(house.getHouseId(), sprinkler, weather, camera);
    }

}
