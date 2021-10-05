package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonDeserialize
public class SurveillanceStationInfo {

    private Version version;
    private String path;
    private int customizedPortHttp;
    private int customizedPortHttps;
    private int cameraNumber;
    private int licenseNumber;
    private int maxCameraSupport;
    private String serial;
    private boolean isAdmin;
    private int userPriv;
    private boolean isLicenseEnough;
    private boolean allowSnapshot;
    private boolean allowManualRec;
    private boolean allowDeleteRec;

    @JsonCreator
    public SurveillanceStationInfo(
            @JsonProperty("version") Version version,
            @JsonProperty("path") String path,
            @JsonProperty("customizedPortHttp") int customizedPortHttp,
            @JsonProperty("customizedPortHttps") int customizedPortHttps,
            @JsonProperty("cameraNumber") int cameraNumber,
            @JsonProperty("licenseNumber") int licenseNumber,
            @JsonProperty("maxCameraSupport") int maxCameraSupport,
            @JsonProperty("serial") String serial,
            @JsonProperty("isAdmin") boolean isAdmin,
            @JsonProperty("userPriv") int userPriv,
            @JsonProperty("isLicenseEnough") boolean isLicenseEnough,
            @JsonProperty("allowSnapshot") boolean allowSnapshot,
            @JsonProperty("allowManualRec") boolean allowManualRec,
            @JsonProperty("allowDeleteRec") boolean allowDeleteRec) {
        this.version = version;
        this.path = path;
        this.customizedPortHttp = customizedPortHttp;
        this.customizedPortHttps = customizedPortHttps;
        this.cameraNumber = cameraNumber;
        this.licenseNumber = licenseNumber;
        this.maxCameraSupport = maxCameraSupport;
        this.serial = serial;
        this.isAdmin = isAdmin;
        this.userPriv = userPriv;
        this.isLicenseEnough = isLicenseEnough;
        this.allowSnapshot = allowSnapshot;
        this.allowManualRec = allowManualRec;
        this.allowDeleteRec = allowDeleteRec;
    }

}
