package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.Garden;

/**
 * Created by Nika Doghonadze
 */
public interface Repository {
    Garden findGarden(String houseId);
    void updateGarden(Garden garden);
    Iterable<Garden> getGardens();
}
