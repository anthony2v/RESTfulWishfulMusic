package com.spicecrispies.core.logging;

import javax.xml.ws.WebFault;

@WebFault(name = "LogFault")
public class LogFault extends Exception {
    public LogFault(String message) {
        super(message);
    }
}

