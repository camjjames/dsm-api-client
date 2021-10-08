package org.flaad.dsm.client;

import org.flaad.dsm.client.model.DsmApiResponse;
import org.flaad.dsm.client.model.SurveillanceStationCameraDetail;
import org.flaad.dsm.client.request.CameraListRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

class SurveillanceCameraListApiClientTest extends DsmApiClient {

    @Test
    void whenGetSurveillanceCameraList_thenCameraDetailsListIsReturned() throws IOException {
        setupWireMock_DsmSurveillanceCameraEndpoint("stubs/api-surveillance-camera-list-success.json");

        CameraListRequest request = CameraListRequest.builder().version(9).method("List").build();
        DsmApiResponse<SurveillanceStationCameraDetail.CameraList> response = surveillanceClient.getSurveillanceCameraList(request);

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getError(), is(nullValue()));

        assertThat(response.getData().getCameras(), is(notNullValue()));
        assertThat(response.getData().getCameras().size(), equalTo(6));

        assertThat(response.getData().getCameras().get(0).getDsId(), equalTo(0));
        assertThat(response.getData().getCameras().get(0).isEnableLowProfile(), equalTo(true));
        assertThat(response.getData().getCameras().get(0).isEnableRecordingKeepSize(), equalTo(true));
        assertThat(response.getData().getCameras().get(0).isEnableRecordingKeepDays(), equalTo(false));
        assertThat(response.getData().getCameras().get(0).getIp(), equalTo("10.1.10.240"));
        assertThat(response.getData().getCameras().get(0).getModel(), equalTo("abcd"));
        assertThat(response.getData().getCameras().get(0).getNewName(), equalTo("1"));
        assertThat(response.getData().getCameras().get(0).getPort(), equalTo(555));
        assertThat(response.getData().getCameras().get(0).getPostRecordTime(), equalTo(5));
        assertThat(response.getData().getCameras().get(0).getPreRecordTime(), equalTo(5));
        assertThat(response.getData().getCameras().get(0).getRecordTime(), equalTo(30));
        assertThat(response.getData().getCameras().get(0).getRecordingKeepSize(), equalTo("200"));
        assertThat(response.getData().getCameras().get(0).getRecordingKeepDays(), equalTo(30));
        assertThat(response.getData().getCameras().get(0).getVendor(), equalTo("HIKVISION"));
    }

    @Test
    void whenGetSurveillanceCameraList_thenFailedToGetCamerasIsReturned() throws IOException {
        setupWireMock_DsmSurveillanceCameraEndpoint("stubs/api-surveillance-camera-list-failure.json");

        CameraListRequest request = CameraListRequest.builder().version(9).method("List").build();
        DsmApiResponse<SurveillanceStationCameraDetail.CameraList> response = surveillanceClient.getSurveillanceCameraList(request);

        assertThat(response.isSuccess(), is(false));
        assertThat(response.getData(), is(nullValue()));
        assertThat(response.getError(), is(notNullValue()));

        assertThat(response.getError().getCode(), equalTo(103));
        assertThat(response.getError().getErrors(), is(notNullValue()));
        assertThat(response.getError().getDetails(), is(notNullValue()));
    }

}