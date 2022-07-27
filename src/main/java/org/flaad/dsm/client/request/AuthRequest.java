package org.flaad.dsm.client.request;

import feign.Param;
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

    @Param("enable_syno_token")
    String enableSynoToken;
    @Param("otp_code")
    String otpCode;
    @Param("enable_device_token")
    String enableDeviceToken;
    @Param("device_id")
    String deviceId;
    @Param("device_name")
    String deviceName;

}
