package com.spicecrispies.core.exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "LogFault",targetNamespace = "http://localhost:8090/RepException")
public class RepException extends Exception {
    public RepException(String message) {
        super(message);
    }
}