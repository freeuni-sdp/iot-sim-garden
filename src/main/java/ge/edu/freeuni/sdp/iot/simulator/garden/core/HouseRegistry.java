package ge.edu.freeuni.sdp.iot.simulator.garden.core;

import ge.edu.freeuni.sdp.iot.simulator.garden.model.HouseData;

import java.util.List;

/**
 * Created by Nika Doghonadze
 */
interface HouseRegistry {
    List<HouseData> getAllHouses();
    HouseData getHouseData(String houseId);
}
