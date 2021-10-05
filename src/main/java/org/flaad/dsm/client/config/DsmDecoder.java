package org.flaad.dsm.client.config;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.flaad.dsm.client.model.AuthSessionToken;
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

    public DsmDecoder(Decoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        Object feignResponse = delegate.decode(response, type);

        if (feignResponse instanceof DsmApiResponse && ((DsmApiResponse<?>) feignResponse).getData() instanceof AuthSessionToken) {
            authDetails.setAuthToken(((AuthSessionToken) ((DsmApiResponse<?>) feignResponse).getData()).getSid());
        }

        return feignResponse;
    }

}
