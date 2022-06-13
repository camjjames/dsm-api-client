package org.flaad.dsm.client.request;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AuthRequest {

    @Builder.Default
    int version = 6;
    @Builder.Default
    String method = "Login";

    String account;
    String passwd;

    String session;
    String format;
    String enable_syno_token;
    String otp_code;
    String enable_device_token;
    String device_id;
    String device_name;

}
