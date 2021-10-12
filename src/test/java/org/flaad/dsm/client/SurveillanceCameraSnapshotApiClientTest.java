package org.flaad.dsm.client;

import org.flaad.dsm.client.request.CameraSnapshotRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

class SurveillanceCameraSnapshotApiClientTest extends DsmApiClient {

    @Test
    void whenGetSurveillanceCameraSnapshot_thenSnapshotIsReturned() throws IOException {
        setupWireMock_DsmSurveillanceCameraSnapshotEndpoint("images/snapshot.jpg");

        CameraSnapshotRequest request = CameraSnapshotRequest.builder().id("1").profileType(0).build();
        ResponseEntity<byte[]> response = surveillanceClient.getSurveillanceCameraSnapshot(request);

        assertThat(response, is(notNullValue()));
    }

}