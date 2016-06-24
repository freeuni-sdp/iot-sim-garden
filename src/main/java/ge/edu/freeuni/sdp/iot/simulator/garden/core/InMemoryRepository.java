package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.Garden;

import java.util.Map;

/**
 * Created by Nika Doghonadze
 */
public class InMemoryRepository implements Repository {
    private Map<String, Garden> gardenMap;

    public InMemoryRepository(Map<String, Garden> gardenMap) {
        this.gardenMap = gardenMap;
    }

    @Override
    public Garden findGarden(String houseId) {
        if (gardenMap.containsKey(houseId))
            return gardenMap.get(houseId);
        return null;
    }

    @Override
    public void updateGarden(Garden garden) {
        gardenMap.put(garden.getHouseId(), garden);
    }
}
