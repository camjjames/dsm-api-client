package org.flaad.dsm.client;

import org.flaad.dsm.client.model.ApiInfo;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class InfoApiClientTest extends DsmApiClient {

    @Test
    void whenGetApiInfo_thenApiListIsReturned() throws IOException {
        setupWireMock_DsmInfoEndpoint("stubs/api-info-get-success.json", 1, "query", "all");
        DsmApiResponse<ApiInfo.ApiInfoList> response = dsmClient.getApiInfo(1, "query", "all");

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getError(), is(nullValue()));

        assertThat(response.getData().getApiInfos().size(), equalTo(665));
    }

    @Test
    void whenGetApiInfoForAuth_thenApiListIsReturned() throws IOException {
        setupWireMock_DsmInfoEndpoint("stubs/api-info-get-single-success.json", 1, "query", "SYNO.API.auth");
        DsmApiResponse<ApiInfo.ApiInfoList> response = dsmClient.getApiInfo(1, "query", "SYNO.API.auth");

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getError(), is(nullValue()));

        assertThat(response.getData().getApiInfos().size(), equalTo(1));
        assertThat(response.getData().getApiInfos().get(0).getApi(), equalTo("SYNO.API.Auth"));
        assertThat(response.getData().getApiInfos().get(0).getMaxVersion(), equalTo("7"));
        assertThat(response.getData().getApiInfos().get(0).getMinVersion(), equalTo("1"));
        assertThat(response.getData().getApiInfos().get(0).getPath(), equalTo("entry.cgi"));
    }
}