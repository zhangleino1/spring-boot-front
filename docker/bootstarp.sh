#!/bin/bash
cd /home/workspace
JAVA_OPTS="${JAVA_OPTS} ${JVM_OPTS}"
BOOT_OPTS="${BOOT_OPTS}"
echo "app-market start! JAVA_OPTS:[${JAVA_OPTS}]"
java -jar ${JAVA_OPTS} -Dlogging.config=./logback-spring.xml app-market-web-0.0.1-SNAPSHOT.jar ${BOOT_OPTS}