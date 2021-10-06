package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
@JsonDeserialize
public class SurveillanceStationInfo {

    private final Version version;
    private final String path;
    private final int customizedPortHttp;
    private final int customizedPortHttps;
    private final int cameraNumber;
    private final int liscenseNumber;
    private final int maxCameraSupport;
    private final int maxLanPort;
    private final String serial;
    private final int userPriv;
    private final boolean isLicenseEnough;

    @JsonCreator
    public SurveillanceStationInfo(
            @JsonProperty("version") Version version,
            @JsonProperty("path") String path,
            @JsonProperty("customizedPortHttp") int customizedPortHttp,
            @JsonProperty("customizedPortHttps") int customizedPortHttps,
            @JsonProperty("cameraNumber") int cameraNumber,
            @JsonProperty("liscenseNumber") int liscenseNumber,
            @JsonProperty("maxCameraSupport") int maxCameraSupport,
            @JsonProperty("maxlanport") int maxLanPort,
            @JsonProperty("serial") String serial,
            @JsonProperty("userPriv") int userPriv,
            @JsonProperty("isLicenseEnough") boolean isLicenseEnough) {
        this.version = version;
        this.path = path;
        this.customizedPortHttp = customizedPortHttp;
        this.customizedPortHttps = customizedPortHttps;
        this.cameraNumber = cameraNumber;
        this.liscenseNumber = liscenseNumber;
        this.maxCameraSupport = maxCameraSupport;
        this.maxLanPort = maxLanPort;
        this.serial = serial;
        this.userPriv = userPriv;
        this.isLicenseEnough = isLicenseEnough;
    }

}
