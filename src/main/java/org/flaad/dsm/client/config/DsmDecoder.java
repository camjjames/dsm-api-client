package org.flaad.dsm.client.config;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.flaad.dsm.client.model.ApiInfo;
import org.flaad.dsm.client.model.AuthSessionToken;
import org.flaad.dsm.client.model.DsmApiInformation;
import org.flaad.dsm.client.model.DsmApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Synology DSM decoder, Only used to get the SID value when authenticating, otherwise will fall through to
 * whatever decoder you really want to use.
 */
@Slf4j
@Component
public class DsmDecoder implements Decoder {

    private final Decoder delegate;

    @Autowired
    private DsmAuthenticationToken authDetails;
    @Autowired
    private DsmApiInformation apiInfo;

    public DsmDecoder(Decoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        Object feignResponse = delegate.decode(response, type);

        if (feignResponse instanceof DsmApiResponse) {
            if (!apiInfo.isSetup() && ((DsmApiResponse<?>) feignResponse).getData() instanceof ApiInfo.ApiInfoList) {
                log.debug("Saving API Details for later use!");
                constructApiInfo(((DsmApiResponse<ApiInfo.ApiInfoList>) feignResponse).getData());
                apiInfo.setSetup(true);
            }

            if (((DsmApiResponse<?>) feignResponse).getData() instanceof AuthSessionToken) {
                log.debug("Saving Auth Security Token for later use!");
                authDetails.setAuthToken(((AuthSessionToken) ((DsmApiResponse<?>) feignResponse).getData()).getSid());
            }
        }

        return feignResponse;
    }

    private void constructApiInfo(ApiInfo.ApiInfoList data) {
        for (ApiInfo info : data.getApiInfos()) {
            apiInfo.addApiDetail(info.getApi(), info.getMinVersion(), info.getMaxVersion(), info.getPath(), info.getRequestFormat());
        }
    }

}
