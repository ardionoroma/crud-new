# First stage: complete build environment
FROM maven:3.8.3-openjdk-11-slim AS builder
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src src
RUN mvn clean package spring-boot:repackage


FROM registry.access.redhat.com/ubi8/openjdk-11
LABEL maintainer="roma.nugraha@i-3.co.id"
COPY --from=builder target/crud-latest.jar /opt/
COPY docker-entrypoint.sh /opt/
USER root 
RUN ln -sf /usr/share/zoneinfo/Asia/Jakarta /etc/localtime
RUN microdnf --nodocs install yum
RUN microdnf --nodocs install git
RUN dnf install iproute -y
RUN git config --global http.sslVerify false
WORKDIR /opt
ENTRYPOINT ["sh", "docker-entrypoint.sh"]
