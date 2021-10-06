package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonDeserialize
public class AuthSessionToken {

    private final String did;
    private final String sid;
    private final boolean isPortalPort;

    @JsonCreator
    private AuthSessionToken(@JsonProperty("did") String did, @JsonProperty("sid") String sid, @JsonProperty("is_portal_port") boolean portalPort) {
        this.did = did;
        this.sid = sid;
        this.isPortalPort = portalPort;
    }

}
