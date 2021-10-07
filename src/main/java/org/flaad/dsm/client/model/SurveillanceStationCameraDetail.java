package org.flaad.dsm.client.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonDeserialize
public class SurveillanceStationCameraDetail {

    int diNum;
    int doNum;
    int audioCodec;
    String channel;
    int dsId;
    boolean enableLowProfile;
    boolean enableRecordingKeepDays;
    boolean enableRecordingKeepSize;
    String fov;
    int highProfileStreamNo;
    int id;
    int idOnRecServer;
    String ip;
    int lowProfileStreamNo;
    int mediumProfileStreamNo;
    String model;
    String newName;
    int port;
    int postRecordTime;
    int preRecordTime;
    int recordTime;
    int recordingKeepDays;
    String recordingKeepSize;
    int status;
    int tvStandard;
    String vendor;
    int videoCodec;
    String videoMode;

    @Getter
    @NoArgsConstructor
    @JsonDeserialize
    public static class CameraList {
        List<SurveillanceStationCameraDetail> cameras = new ArrayList<>();
    }

}
