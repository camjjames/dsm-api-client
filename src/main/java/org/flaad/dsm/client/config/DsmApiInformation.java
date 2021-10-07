package org.flaad.dsm.client.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class DsmApiInformation {

    private boolean setup = false;
    private final List<ApiDetails> apiList = new ArrayList<>();

    public void setSetup(boolean hasBeenSetup) {
        this.setup = hasBeenSetup;
    }

    public void addApiDetail(String api, String minVersion, String maxVersion, String path, String requestFormat) {
        apiList.add(new ApiDetails(api, minVersion, maxVersion, path, requestFormat));
    }

    @Getter
    @AllArgsConstructor
    private static class ApiDetails {
        private final String api;
        private final String minVersion;
        private final String maxVersion;
        private final String path;
        private final String requestFormat;
    }

}
