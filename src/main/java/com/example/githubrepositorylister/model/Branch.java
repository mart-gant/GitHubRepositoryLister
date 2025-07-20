package com.example.githubrepositorylister.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

    private String name;
    private Commit commit;

}
