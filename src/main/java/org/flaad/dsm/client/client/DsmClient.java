package org.flaad.dsm.client.client;

import org.flaad.dsm.client.config.DsmApiClientConfiguration;
import org.flaad.dsm.client.model.ApiInfo;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DsmClient", url = "${dsm.url}", configuration = DsmApiClientConfiguration.class)
public interface DsmClient {

    @GetMapping(path = "/webapi/query.cgi?api=SYNO.API.Info", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<ApiInfo.ApiInfoList> getApiInfo(@RequestParam(name = "version", defaultValue = "1") int version,
                                        @RequestParam(name = "method", defaultValue = "query") String method,
                                        @RequestParam(name = "query", defaultValue = "all") String query);

}
