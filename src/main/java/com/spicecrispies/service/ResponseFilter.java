package com.spicecrispies.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.*;
import java.io.*;
import java.util.Date;

@Provider
public class ResponseFilter implements WriterInterceptor {
    @Context
    private UriInfo uriInfo;
    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        System.out.println("-- in MyInterceptor#aroundWriteTo() --");
        if (!uriInfo.getPath().contains("login") && !uriInfo.getPath().contains("register")) {
            context.proceed();
            return;
        }

        Date timeNow = new Date();
        OutputStream originalStream = context.getOutputStream();
        final ByteArrayOutputStream mem = new ByteArrayOutputStream();
        context.setOutputStream(mem);
        context.proceed();

        byte[] data = mem.toByteArray();// The response is now in the memory stream.
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(inputStream);
        ((ObjectNode) tree).put("time", timeNow.toString());   // Adding the timestamp
        data = mapper.writeValueAsBytes(tree);

        // Setting the output stream back to the original and writing the modified response
        originalStream.write(data, 0, data.length);
        context.setOutputStream(originalStream);
    }

}
