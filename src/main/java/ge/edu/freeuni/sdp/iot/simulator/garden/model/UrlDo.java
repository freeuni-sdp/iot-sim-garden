package ge.edu.freeuni.sdp.iot.simulator.garden.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by misho on 6/26/16.
 */
public class UrlDo {

    @JsonProperty(required = true)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
