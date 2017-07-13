package ru.sbt.http.model;

/**
 * Created by sbt-yakimov-vi on 11.07.2017.
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "maxTTL"
})
public class CachePolicy {

    @JsonProperty("type")
    private String type;
    @JsonProperty("maxTTL")
    private Integer maxTTL;

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("maxTTL")
    public Integer getMaxTTL() {
        return maxTTL;
    }

    @JsonProperty("maxTTL")
    public void setMaxTTL(Integer maxTTL) {
        this.maxTTL = maxTTL;
    }

}