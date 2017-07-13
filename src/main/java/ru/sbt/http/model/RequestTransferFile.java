package ru.sbt.http.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;




@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "fileId",
        "user",
        "scenarioId",
        "cachePolicy"
})
public class RequestTransferFile {

    @JsonProperty("fileId")
    private String fileId;
    @JsonProperty("user")
    private String user;
    @JsonProperty("scenarioId")
    private String scenarioId;
    @JsonProperty("cachePolicy")
    private CachePolicy cachePolicy;

    @JsonProperty("fileId")
    public String getFileId() {
        return fileId;
    }

    @JsonProperty("fileId")
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("scenarioId")
    public String getScenarioId() {
        return scenarioId;
    }

    @JsonProperty("scenarioId")
    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    @JsonProperty("cachePolicy")
    public CachePolicy getCachePolicy() {
        return cachePolicy;
    }

    @JsonProperty("cachePolicy")
    public void setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
    }

}