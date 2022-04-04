package org.flaad.dsm.client.client;

import org.flaad.dsm.client.request.AuthRequest;
import org.flaad.dsm.client.request.DsmRequest;
import org.flaad.dsm.client.model.AuthSessionToken;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "DsmAuthClient", url = "${dsm.url}", configuration = DsmApiClientConfiguration.class)
public interface DsmAuthClient {

    @GetMapping(path = "/webapi/query.cgi?api=SYNO.API.Auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<AuthSessionToken> login(@SpringQueryMap AuthRequest authRequest);

    @GetMapping(path = "/webapi/query.cgi?api=SYNO.API.Auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    void logout(@SpringQueryMap DsmRequest dsmRequest);


}
