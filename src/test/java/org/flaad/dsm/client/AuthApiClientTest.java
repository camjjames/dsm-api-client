package org.flaad.dsm.client;

import org.flaad.dsm.client.model.AuthSessionToken;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.flaad.dsm.client.model.SurveillanceStationInfo;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

class AuthApiClientTest extends DsmApiClient {

    @Test
    @Order(1)
    @DirtiesContext
    void whenGetAuthTokenApi_thenAuthTokenIsReturned() throws IOException {
        setupWireMock_DsmAuthEndpoint("stubs/api-auth-get-success.json", 6, "login", "user", "pwd");
        DsmApiResponse<AuthSessionToken> response = authClient.login(6, "login", "user", "pwd");

        assertThat(response.isSuccess(), is(true));
        assertThat(response.getData(), is(notNullValue()));
        assertThat(response.getError(), is(nullValue()));

        assertThat(response.getData().getSid(), equalTo("xsv0LlUcCf16V4Sp9XdGBirVp0BcxL6x91f-crJSCis7lSsmTxI_f5vNagdmwKGp3C2vpOJW2TQXBuNAyxPEok"));
        assertThat(response.getData().getDid(), equalTo("Uu7ByHpmH0TaAdiRJFH2kGjc8lBVdNezfdfdFIAPUsolvvy9f80ndshAa5y1n6AoDFPz0Fjt5NWPzkVAjrS5Fw"));
        assertThat(response.getData().isPortalPort(), is(false));
    }

    @Test
    @Order(2)
    void whenGetAuthTokenApi_thenPasswordIncorrectErrorReturned() throws IOException {
        setupWireMock_DsmAuthEndpoint("stubs/api-auth-incorrect-password-failure.json", 6, "login", "user", "xxxx");
        DsmApiResponse<AuthSessionToken> response = authClient.login(6, "login", "user", "xxxx");

        assertThat(response.isSuccess(), is(false));
        assertThat(response.getData(), is(nullValue()));
        assertThat(response.getError(), is(notNullValue()));

        assertThat(response.getError().getCode(), equalTo(400));
    }

}