package org.flaad.dsm.client.client;

import org.flaad.dsm.client.request.ApiInfoRequest;
import org.flaad.dsm.client.model.ApiInfo;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "DsmClient", url = "${dsm.url}", configuration = DsmApiClientConfiguration.class)
public interface DsmClient {

    @GetMapping(path = "/webapi/query.cgi?api=SYNO.API.Info", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<ApiInfo.ApiInfoList> getApiInfo(@SpringQueryMap ApiInfoRequest apiInfoRequest);

}
