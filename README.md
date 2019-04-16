# Embedded Undertow Hello Application

A minimal embedded Undertow Hello World application.

## Build

    mvn clean install

## Run locally

    mvn exec:java

    curl http://localhost:8080?name=World

## Deploy to OpenShift

    mvn fabric8:deploy -Popenshift
