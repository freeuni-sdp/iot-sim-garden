package ge.edu.freeuni.sdp.iot.simulator.garden.model;

public class Camera {

    private String imageUrl;

    public Camera() {
    }

    public Camera(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
