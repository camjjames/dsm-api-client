package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonDeserialize
public class DsmApiResponse<T> {

    private boolean success;
    private T data;
    private DsmApiError error;

    @JsonCreator
    public DsmApiResponse(@JsonProperty("success") boolean success, @JsonProperty("data") T data, @JsonProperty("error") DsmApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

}
