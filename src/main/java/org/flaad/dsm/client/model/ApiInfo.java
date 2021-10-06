package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@JsonDeserialize
@Component
public class ApiInfo {

    private final String api;
    private final String minVersion;
    private final String maxVersion;
    private final String path;
    private final String requestFormat;

    public ApiInfo(String api, String minVersion, String maxVersion, String path, String requestFormat) {
        this.api = api;
        this.minVersion = minVersion;
        this.maxVersion = maxVersion;
        this.path = path.replaceAll("^_+", "").trim();
        this.requestFormat = requestFormat;
    }

    private ApiInfo(String api, ApiInfo source) {
        this(api, source.getMinVersion(), source.getMaxVersion(), source.getPath(), source.getRequestFormat());
    }

    @JsonCreator
    public ApiInfo(@JsonProperty("minVersion") String minVersion,
                    @JsonProperty("maxVersion") String maxVersion,
                    @JsonProperty("path") String path,
                    @JsonProperty("requestFormat") String requestFormat) {
        this(null, minVersion, maxVersion, path, requestFormat);
    }

    public static class ApiInfoList {
        private final List<ApiInfo> apiInfos = new ArrayList<>();

        @JsonAnySetter
        public void add(String key, ApiInfo value) {
            apiInfos.add(new ApiInfo(key, value));
        }

        public List<ApiInfo> getApiInfos() {
            return Collections.unmodifiableList(apiInfos);
        }
    }
}

