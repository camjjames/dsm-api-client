package org.flaad.dsm.client;

import org.apache.commons.io.IOUtils;
import org.flaad.dsm.client.client.DsmAuthClient;
import org.flaad.dsm.client.client.DsmClient;
import org.flaad.dsm.client.client.DsmSurveillanceClient;
import org.flaad.dsm.client.config.DsmConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

@ActiveProfiles("test")
@EnableAutoConfiguration
@AutoConfigureWireMock(port = DsmApiClient.port)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { DsmConfiguration.class })
class DsmApiClient {

    public static final int port = 9561;

    @Autowired
    DsmClient dsmClient;
    @Autowired
    DsmAuthClient authClient;
    @Autowired
    DsmSurveillanceClient surveillanceClient;

    /* Set up WireMock for synology API info endpoint */
    void setupWireMock_DsmInfoEndpoint(String location, int version, String method, String query) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/query.cgi?api=SYNO.API.Info")
                .append("&version=").append(version)
                .append("&method=").append(method)
                .append("&query=").append(query);
        setupStub(url.toString(), location);
    }

    /* Set up WireMock for synology API info endpoint */
    void setupWireMock_DsmAuthEndpoint (String location, int version, String method, String username, String password) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/query.cgi?api=SYNO.API.Auth")
                .append("&version=").append(version)
                .append("&method=").append(method)
                .append("&account=").append(username)
                .append("&passwd=").append(password);
        setupStub(url.toString(), location);
    }

    /* Set up WireMock for synology API info endpoint */
    void setupWireMock_DsmSidAuthEndpoint (String location, int version, String method, String username, String password, String sid) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/query.cgi?api=SYNO.API.Auth")
                .append("&version=").append(version)
                .append("&method=").append(method)
                .append("&account=").append(username)
                .append("&passwd=").append(password)
                .append("&_sid=").append(sid);
        setupStub(url.toString(), location);
    }

    /* Set up WireMock for synology Surveillance API info endpoint */
    void setupWireMock_DsmSurveillanceInfoEndpoint(String location, int version, String method) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/entry.cgi?api=SYNO.SurveillanceStation.Info")
                .append("&version=").append(version)
                .append("&method=").append(method);
        setupStub(url.toString(), location);
    }

    /* Set up WireMock for synology Surveillance Camera API endpoint */
    void setupWireMock_DsmSurveillanceCameraEndpoint(String location, int version, String method) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/entry.cgi?api=SYNO.SurveillanceStation.Camera")
                .append("&version=").append(version)
                .append("&method=").append(method);
        setupStub(url.toString(), location);
    }

    /* Set up WireMock for synology Surveillance Camera API endpoint */
    void setupWireMock_DsmSurveillanceCameraEndpoint(String location, int version, String method, String cameras) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/entry.cgi?api=SYNO.SurveillanceStation.Camera")
                .append("&version=").append(version)
                .append("&method=").append(method)
                .append("&cameras=").append(cameras);
        setupStub(url.toString(), location);
    }




    /* Define the stub for the Test. */
    private void setupStub(String url, String location) throws IOException {
        stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read(location))));
    }

    /* Read a file from the filesystem and return its contents as a String */
    private String read(String location) throws IOException {
        return IOUtils.toString(new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8);
    }
}
