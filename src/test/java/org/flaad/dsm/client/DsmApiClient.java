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
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

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
    void setupWireMock_DsmInfoEndpoint(String location) throws IOException {
        setupStub("\\/webapi\\/query\\.cgi\\?api=SYNO\\.API\\.Info(\\&(.)*)?", location);
    }

    /* Set up WireMock for synology API info endpoint */
    void setupWireMock_DsmAuthEndpoint (String location) throws IOException {
        setupStub("\\/webapi\\/query\\.cgi\\?api=SYNO\\.API\\.Auth(\\&(.)*)?", location);
    }

    /* Set up WireMock for synology Surveillance API info endpoint */
    void setupWireMock_DsmSurveillanceInfoEndpoint(String location) throws IOException {
        setupStub("\\/webapi\\/entry\\.cgi\\?api=SYNO\\.SurveillanceStation\\.Info(\\&(.)*)?", location);
    }

    /* Set up WireMock for synology Surveillance Camera API endpoint */
    void setupWireMock_DsmSurveillanceCameraEndpoint(String location) throws IOException {
        setupStub("\\/webapi\\/entry\\.cgi\\?api=SYNO\\.SurveillanceStation\\.Camera(\\&(.)*)?", location);
    }


    /* Define the stub for the Test. */
    private void setupStub(String url, String location) throws IOException {
        stubFor(get(urlMatching(url))
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
