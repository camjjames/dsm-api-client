package org.flaad.dsm.client.client;

import org.flaad.dsm.client.model.DsmApiResponse;
import org.flaad.dsm.client.model.SurveillanceStationCameraDetail;
import org.flaad.dsm.client.model.SurveillanceStationCameraInfo;
import org.flaad.dsm.client.model.SurveillanceStationInfo;
import org.flaad.dsm.client.request.CameraInfoRequest;
import org.flaad.dsm.client.request.CameraListRequest;
import org.flaad.dsm.client.request.CameraSnapshotRequest;
import org.flaad.dsm.client.request.DsmRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "DsmSurveillanceClient", url = "${dsm.url}", configuration = DsmApiClientConfiguration.class)
public interface DsmSurveillanceClient {

    @GetMapping(path = "/webapi/entry.cgi?api=SYNO.SurveillanceStation.Info", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<SurveillanceStationInfo> getSurveillanceInfo(@SpringQueryMap DsmRequest cameraRequest);

    @GetMapping(path = "/webapi/entry.cgi?api=SYNO.SurveillanceStation.Camera", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<SurveillanceStationCameraDetail.CameraList> getSurveillanceCameraList(@SpringQueryMap CameraListRequest cameraRequest);

    @GetMapping(path = "/webapi/entry.cgi?api=SYNO.SurveillanceStation.Camera", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<SurveillanceStationCameraInfo.CameraList> getSurveillanceCameraInfo(@SpringQueryMap CameraInfoRequest cameraRequest);

    @GetMapping(path = "/webapi/entry.cgi?api=SYNO.SurveillanceStation.Camera", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<byte[]> getSurveillanceCameraSnapshot(@SpringQueryMap CameraSnapshotRequest cameraSnapshotRequest);


}
