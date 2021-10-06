package org.flaad.dsm.client.client;

import org.flaad.dsm.client.config.DsmApiClientConfiguration;
import org.flaad.dsm.client.model.AuthSessionToken;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DsmAuthClient", url = "${dsm.url}", configuration = DsmApiClientConfiguration.class)
public interface DsmAuthClient {

    @GetMapping(path = "/webapi/query.cgi?api=SYNO.API.Auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<AuthSessionToken> login(@RequestParam(name = "version", defaultValue = "6") int version,
                                           @RequestParam(name = "method", defaultValue = "login") String method,
                                           @RequestParam(name = "account") String account,
                                           @RequestParam(name = "passwd") String passwd);

    @GetMapping(path = "/webapi/query.cgi?api=SYNO.API.Auth", consumes = MediaType.APPLICATION_JSON_VALUE)
    void logout(@RequestParam(name = "version", defaultValue = "6") int version,
                @RequestParam(name = "method", defaultValue = "logout") String method);


}
