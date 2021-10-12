package org.flaad.dsm.client.request;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CameraSnapshotRequest {

    @Builder.Default
    int version = 9;
    @Builder.Default
    String method = "GetSnapshot";
    private String id;
    private String name;
    private int dsid;
    private int profileType;

}
