package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonDeserialize
public class SurveillanceStationInfo {

    int cameraNumber;
    @JsonProperty("customizedPortHttp")
    int httpPort;
    @JsonProperty("customizedPortHttps")
    int httpsPort;
    boolean enableVideoRelay;
    String hostname;
    int isLicenseEnough;
    @JsonProperty("liscenseNumber")
    int licenseNumber;
    int maxCameraSupport;
    String path;
    String pluginHelperVersion;
    String productName;
    String serial;
    String timezone;
    String timezoneTZDB;
    int uid;
    @JsonProperty("userPriv")
    int userPrivilege;
    String webPluginVersion;
    Version version;

}
