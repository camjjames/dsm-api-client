package org.flaad.dsm.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonDeserialize
public class SurveillanceStationInfo {

    int cameraNumber;
    int customizedPortHttp;
    int customizedPortHttps;
    boolean enableVideoRelay;
    String hostname;
    int isLicenseEnough;
    int liscenseNumber;
    int maxCameraSupport;
    String path;
    String pluginHelperVersion;
    String productName;
    String serial;
    String timezone;
    String timezoneTZDB;
    int uid;
    int userPriv;
    String webPluginVersion;
    Version version;

}
