package org.flaad.dsm.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonDeserialize
public class SurveillanceStationCameraInfo {

    String id;
    boolean blAudioPriv;
    boolean blLiveviewPriv;
    String camPath;
    int privilege;


    @Getter
    @NoArgsConstructor
    @JsonDeserialize
    public static class CameraList {
        List<SurveillanceStationCameraInfo> cameras = new ArrayList<>();
    }

}
