package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonDeserialize
public class Version {

    private final int major;
    private final int minor;
    private final int small;
    private final int build;

    @JsonCreator
    public Version(
            @JsonProperty("major") int major,
            @JsonProperty("minor") int minor,
            @JsonProperty("small") int small,
            @JsonProperty("build") int build) {
        this.major = major;
        this.minor = minor;
        this.small = small;
        this.build = build;
    }

}
