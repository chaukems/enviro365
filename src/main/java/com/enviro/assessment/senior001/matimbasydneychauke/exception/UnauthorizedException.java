package com.enviro.assessment.senior001.matimbasydneychauke.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UnauthorizedException(String msg) {
        super(msg);
    }

}