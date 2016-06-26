package ge.edu.freeuni.sdp.iot.simulator.garden.services;

import ge.edu.freeuni.sdp.iot.simulator.garden.core.Repository;
import ge.edu.freeuni.sdp.iot.simulator.garden.core.RepositoryFactory;
import ge.edu.freeuni.sdp.iot.simulator.garden.model.*;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

    @GET
    @Path("{id}/temperature")
    public TemperatureStatus getTemperatureStatus(@PathParam("id") String houseId) {
        Repository repository = getRepository();
        Garden garden = repository.findGarden(houseId);

        if (garden == null)
            throw new NotFoundException();

        return TemperatureStatus.fromGarden(garden);
    }

    @GET
    @Path("{id}/rain")
    public RainStatus getRainStatus(@PathParam("id") String houseId) {
        Repository repository = getRepository();
        Garden garden = repository.findGarden(houseId);

        if (garden == null)
            throw new NotFoundException();

        return RainStatus.fromGarden(garden);
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

    @PUT
    @Path("{id}/temperature")
    public TemperatureStatus putTemperatureOrder(@PathParam("id") String houseId, TemperatureCommand order) {
        if (!order.isValid())
            throw new BadRequestException();

        Repository repository = getRepository();
        Garden garden = repository.findGarden(houseId);

        if (garden == null)
            throw new NotFoundException();

        Weather weather = garden.getWeather();

        weather.setTempC(order.getTempC());
        weather.setTempF(order.getTempF());

        return TemperatureStatus.fromGarden(garden);
    }

    @PUT
    @Path("{id}/rain")
    public RainStatus putRainOrder(@PathParam("id") String houseId, RainCommand order) {
        if (!order.isValid())
            throw new BadRequestException();

        Repository repository = getRepository();
        Garden garden = repository.findGarden(houseId);

        if (garden == null)
            throw new NotFoundException();

        Weather weather = garden.getWeather();

        weather.setWillRain(order.getWillRain());

        return RainStatus.fromGarden(garden);
    }

    @PUT
    @Path("{id}/camera")
    public Response changeStage(@PathParam("id") String houseId, UrlDo urlDo) {
        Garden garden = getRepository().findGarden(houseId);

        if (garden == null)
            throw new NotFoundException();
        if (urlDo.getUrl() == null)
            throw new BadRequestException();
        garden.getCamera().setImageUrl(urlDo.getUrl());

        return Response.ok().build();
    }

    @GET
    @Path("{id}/camera")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public byte[] getStageImage(@PathParam("id") String houseId) throws IOException {
        Garden garden = getRepository().findGarden(houseId);
        if (garden == null)
            throw new NotFoundException();
        String uriString = garden.getCamera().getImageUrl();
        if (uriString == null)
            throw new NotFoundException();
        URL url = new URL(uriString);
        BufferedImage image = ImageIO.read(url);
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", array);
        return array.toByteArray();
    }

}
