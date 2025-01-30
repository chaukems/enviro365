package com.enviro.assessment.senior001.matimbasydneychauke.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(setterPrefix = "with")
@Setter
@Getter
@AllArgsConstructor
public class Response {

    private String resultCode;
    private String errorCode;
    private String resultMessage;
    private String data;

}
