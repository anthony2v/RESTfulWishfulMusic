package com.spicecrispies.core.logging;

import javax.xml.ws.WebFault;

@WebFault(name = "LogFault")
public class RepException extends Exception {
    public RepException(String message) {
        super(message);
    }
}

