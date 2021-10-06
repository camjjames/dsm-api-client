package org.flaad.dsm.client.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DsmApiInformation {

    private boolean setup = false;
    private List<ApiDetails> apiList = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    private static class ApiDetails {
        private String api;
        private String minVersion;
        private String maxVersion;
        private String path;
        private String requestFormat;
    }

    public void addApiDetail(String api, String minVersion, String maxVersion, String path, String requestFormat) {
        apiList.add(new ApiDetails(api, minVersion, maxVersion, path, requestFormat));
    }
}
