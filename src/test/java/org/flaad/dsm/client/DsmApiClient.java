package org.flaad.dsm.client;

import org.apache.commons.io.IOUtils;
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
    DsmSurveillanceClient surveillanceClient;

    /* Set up WireMock for synology API info endpoint */
    void setupWireMock_DsmInfoEndpoint(String location, int version, String method, String query) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/query.cgi?api=SYNO.API.Info")
                .append("&version=").append(version)
                .append("&method=").append(method)
                .append("&query=").append(query);
        stubFor(get(urlEqualTo(url.toString()))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read(location))));
    }

    /* Set up WireMock for synology API info endpoint */
    void setupWireMock_DsmSurveillanceInfoEndpoint(String location, int version, String method) throws IOException {
        StringBuilder url = new StringBuilder("/webapi/query.cgi?api=SYNO.API.Info")
                .append("&version=").append(version)
                .append("&method=").append(method);
        stubFor(get(urlEqualTo(url.toString()))
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
