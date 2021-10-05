package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonDeserialize
public class Version {

    private int major;
    private int minor;
    private int build;

    @JsonCreator
    public Version(
            @JsonProperty("major") int major,
            @JsonProperty("minor") int minor,
            @JsonProperty("build") int build) {
        this.major = major;
        this.minor = minor;
        this.build = build;
    }

}
