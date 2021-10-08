package org.flaad.dsm.client.request;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ApiInfoRequest extends DsmRequest {

    private String query;

}
