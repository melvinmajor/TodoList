#!/bin/sh

mvn clean package && \
    cp cli-client/target/cli-client-1.0-jar-with-dependencies.jar bin/todolist-cli-1.0.jar && \
    cp gui-client/target/gui-client-0.1-jar-with-dependencies.jar bin/todolist-gui-0.1.jar && \
    cp server/target/server-1.0-jar-with-dependencies.jar bin/todolist-server-1.0.jar
