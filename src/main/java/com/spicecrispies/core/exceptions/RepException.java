package com.spicecrispies.core.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "LogFault")
public class RepException extends Exception {
    public RepException(String message) {
        super(message);
    }
}