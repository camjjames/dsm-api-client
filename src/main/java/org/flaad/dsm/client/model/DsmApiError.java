package org.flaad.dsm.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Getter
@NoArgsConstructor
@JsonDeserialize
public class DsmApiError {

    private int code;
    private final List<DsmApiError> errors = new ArrayList<>();
    private final Map<String, String> details = new HashMap<>();

    @JsonCreator
    public DsmApiError(@JsonProperty("code") int code, @JsonProperty("errors") List<DsmApiError> errors) {
        this.code = code;
        this.errors.addAll(ofNullable(errors).orElse(new ArrayList<>()));
    }

}
