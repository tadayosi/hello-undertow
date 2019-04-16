package com.github.tadayosi.undertow;

import java.util.ArrayDeque;
import java.util.Arrays;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloApplication implements HttpHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HelloApplication.class);

    public static void main(String[] args) {
        Undertow undertow = Undertow.builder()
            .addHttpListener(8080, "0.0.0.0")
            .setHandler(new HelloApplication())
            .build();
        undertow.start();
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        String name = exchange.getQueryParameters()
            .getOrDefault("name", new ArrayDeque<>(Arrays.asList("World")))
            .getFirst();
        LOG.info("received: name = {}", name);
        String message = String.format("Hello, %s!\n", name);
        exchange.getResponseSender().send(message);
        exchange.getResponseSender().close();
    }
}
