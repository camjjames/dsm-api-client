package org.flaad.dsm.client.client;

import org.flaad.dsm.client.config.DsmApiClientConfiguration;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.flaad.dsm.client.model.SurveillanceStationInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DsmSurveillanceClient", url = "${dsm.url}", configuration = DsmApiClientConfiguration.class)
public interface DsmSurveillanceClient {

    @GetMapping(path = "/webapi/entry.cgi?api=SYNO.SurveillanceStation.Info", consumes = MediaType.APPLICATION_JSON_VALUE)
    DsmApiResponse<SurveillanceStationInfo> getSurveillanceInfo(@RequestParam(name = "version", defaultValue = "8") int version,
            @RequestParam(name = "method", defaultValue = "GetInfo") String method);

}
