package org.flaad.dsm.client;

import org.flaad.dsm.client.model.DsmApiResponse;
import org.flaad.dsm.client.model.SurveillanceStationCameraInfo;
import org.flaad.dsm.client.request.CameraInfoRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

class SurveillanceCameraInfoApiClientTest extends DsmApiClient {

    @Test
    void whenGetSurveillanceCameraInfo_thenCameraInfoIsReturned() throws IOException {
        setupWireMock_DsmSurveillanceCameraEndpoint("stubs/api-surveillance-camera-1-info-success.json");

        CameraInfoRequest request = CameraInfoRequest.builder().cameras("1").build();
        DsmApiResponse<SurveillanceStationCameraInfo.CameraList> response = surveillanceClient.getSurveillanceCameraInfo(request);

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getError(), is(nullValue()));

        assertThat(response.getData().getCameras(), is(notNullValue()));
        assertThat(response.getData().getCameras().size(), equalTo(1));

        assertThat(response.getData().getCameras().get(0).getId(), is(nullValue()));
        assertThat(response.getData().getCameras().get(0).getPrivilege(), equalTo(31));
        assertThat(response.getData().getCameras().get(0).getCamPath(), equalTo("cnRzcDovL2FkbWluOjIwMjBIVmZyZWQzNjAhQDE5Mi4xNjguMS4yNDA6NTU0L1N0cmVhbWluZy9jaGFubmVscy8xMDI="));
        assertThat(response.getData().getCameras().get(0).isBlAudioPriv(), equalTo(true));
        assertThat(response.getData().getCameras().get(0).isBlLiveviewPriv(), equalTo(true));
    }

    @Test
    void whenGetSurveillanceCameraInfo_thenCameraInfoIsReturned_WithBasic() throws IOException {
        setupWireMock_DsmSurveillanceCameraEndpoint("stubs/api-surveillance-camera-1-info-success.json");

        CameraInfoRequest request = CameraInfoRequest.builder().cameras("1").basic(true).build();
        DsmApiResponse<SurveillanceStationCameraInfo.CameraList> response = surveillanceClient.getSurveillanceCameraInfo(request);

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getError(), is(nullValue()));

        assertThat(response.getData().getCameras(), is(notNullValue()));
        assertThat(response.getData().getCameras().size(), equalTo(1));

        assertThat(response.getData().getCameras().get(0).getId(), is(nullValue()));
        assertThat(response.getData().getCameras().get(0).getPrivilege(), equalTo(31));
        assertThat(response.getData().getCameras().get(0).getCamPath(), equalTo("cnRzcDovL2FkbWluOjIwMjBIVmZyZWQzNjAhQDE5Mi4xNjguMS4yNDA6NTU0L1N0cmVhbWluZy9jaGFubmVscy8xMDI="));
        assertThat(response.getData().getCameras().get(0).isBlAudioPriv(), equalTo(true));
        assertThat(response.getData().getCameras().get(0).isBlLiveviewPriv(), equalTo(true));
    }

    @Test
    void whenGetSurveillanceCameraInfo_thenFailedToGetCameraIsReturned() throws IOException {
        setupWireMock_DsmSurveillanceCameraEndpoint("stubs/api-surveillance-camera-info-failure.json");

        CameraInfoRequest request = CameraInfoRequest.builder().version(8).method("GetInfo").cameras("8").build();
        DsmApiResponse<SurveillanceStationCameraInfo.CameraList> response = surveillanceClient.getSurveillanceCameraInfo(request);

        assertThat(response.isSuccess(), is(false));
        assertThat(response.getData(), is(nullValue()));
        assertThat(response.getError(), is(notNullValue()));

        assertThat(response.getError().getCode(), equalTo(103));
        assertThat(response.getError().getErrors(), is(notNullValue()));
        assertThat(response.getError().getDetails(), is(notNullValue()));
    }

}