package org.flaad.dsm.client.request;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CameraInfoRequest extends DsmRequest {

    private String cameras;
//    private int privCamType;
//    private boolean blIncludeDeletedCam;
    private boolean basic;
    private boolean streamInfo;
    private boolean optimize;
    private boolean ptz;
    private boolean eventDetection;
    private boolean deviceOutCap;
    private boolean fisheye;
    private boolean camAppInfo;

}
