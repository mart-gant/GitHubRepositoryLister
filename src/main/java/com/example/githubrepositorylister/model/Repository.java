package com.example.githubrepositorylister.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {

    private String name;

    @JsonProperty("fork")
    private boolean isFork;

    private Owner owner;

}
