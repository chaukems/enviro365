package com.enviro.assessment.senior001.matimbasydneychauke.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JwtRequest {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;


}