package ge.edu.freeuni.sdp.iot.simulator.garden.services;

import ge.edu.freeuni.sdp.iot.simulator.garden.core.Repository;
import ge.edu.freeuni.sdp.iot.simulator.garden.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.Garden;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.Sprinkler;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.SwitchCommand;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.SwitchStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Nika Doghonadze
 */
@Path("/houses")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class GardenService {

    protected Repository getRepository() {
        return RepositoryFactory.inMemoryRepositoryInstance();
    }

    @GET
    @Path("{id}/sprinkler-switch")
    public SwitchStatus get(@PathParam("id") String houseId) {
        Repository repository = getRepository();
        Garden garden = repository.findGarden(houseId);

        if (garden == null)
            throw new NotFoundException();

        return SwitchStatus.fromGarden(garden);
    }

    @PUT
    @Path("{id}/sprinkler-switch")
    public SwitchStatus put(@PathParam("id") String houseId, SwitchCommand order) {
        if (!order.isValid())
            throw new BadRequestException();

        Repository repository = getRepository();
        Garden garden = repository.findGarden(houseId);

        if (garden == null)
            throw new NotFoundException();


        Sprinkler sprinkler = garden.getSprinkler();

        if (order.getChangeStatusTo().equals("on")) {
            sprinkler.turnOnFor(order.getTimeout());
        } else {
            sprinkler.turnOff();
        }

        return SwitchStatus.fromGarden(garden);
    }

}
