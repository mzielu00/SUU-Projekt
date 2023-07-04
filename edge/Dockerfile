FROM openjdk:17
WORKDIR /usr/src/edge

ENV server.port=8260

COPY target/edge-0.0.1-SNAPSHOT.jar /usr/src/edge
CMD ["java", "-jar", "/usr/src/edge/edge-0.0.1-SNAPSHOT.jar"]