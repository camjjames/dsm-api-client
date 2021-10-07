package org.flaad.dsm.client;

import org.flaad.dsm.client.model.DsmApiResponse;
import org.flaad.dsm.client.model.SurveillanceStationInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

class SurveillanceInfoApiClientTest extends DsmApiClient {

    @Test
    void whenGetSurveillanceApiInfo_thenApiInfoIsReturned() throws IOException {
        setupWireMock_DsmSurveillanceInfoEndpoint("stubs/api-surveillance-info-get-success.json", 8, "GetInfo");
        DsmApiResponse<SurveillanceStationInfo> response = surveillanceClient.getSurveillanceInfo(8, "GetInfo");

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getError(), is(nullValue()));

        assertThat(response.getData().getCameraNumber(), equalTo(6));
        assertThat(response.getData().getPath(), equalTo("/webman/3rdparty/SurveillanceStation/"));
        assertThat(response.getData().getCustomizedPortHttp(), equalTo(222));
        assertThat(response.getData().getCustomizedPortHttps(), equalTo(223));
        assertThat(response.getData().getSerial(), equalTo("123456789"));
        assertThat(response.getData().getLiscenseNumber(), equalTo(6));
        assertThat(response.getData().getMaxCameraSupport(), equalTo(40));
        assertThat(response.getData().getIsLicenseEnough(), equalTo(1));
        assertThat(response.getData().getUserPriv(), equalTo(1));
        assertThat(response.getData().isEnableVideoRelay(), equalTo(false));
        assertThat(response.getData().getHostname(), equalTo("homenas"));
        assertThat(response.getData().getPluginHelperVersion(), equalTo(""));
        assertThat(response.getData().getProductName(), equalTo("DiskStation"));
        assertThat(response.getData().getTimezone(), equalTo("Sydney"));
        assertThat(response.getData().getTimezoneTZDB(), equalTo("Australia/Sydney"));
        assertThat(response.getData().getUid(), equalTo(1028));
        assertThat(response.getData().getWebPluginVersion(), equalTo(""));

        assertThat(response.getData().getVersion(), is(notNullValue()));
        assertThat(response.getData().getVersion().getMajor(), equalTo(8));
        assertThat(response.getData().getVersion().getMinor(), equalTo(2));
        assertThat(response.getData().getVersion().getSmall(), equalTo(9));
        assertThat(response.getData().getVersion().getBuild(), equalTo(7301));
    }

    @Test
    void whenGetSurveillanceApiInfo_thenNotFoundErrorReturned() throws IOException {
        setupWireMock_DsmSurveillanceInfoEndpoint("stubs/api-surveillance-info-get-failure.json", 8, "GetInfoB");
        DsmApiResponse<SurveillanceStationInfo> response = surveillanceClient.getSurveillanceInfo(8, "GetInfoB");

        assertThat(response.isSuccess(), is(false));
        assertThat(response.getData(), is(nullValue()));
        assertThat(response.getError(), is(notNullValue()));

        assertThat(response.getError().getCode(), equalTo(103));
        assertThat(response.getError().getErrors(), is(notNullValue()));
        assertThat(response.getError().getDetails(), is(notNullValue()));
    }

}