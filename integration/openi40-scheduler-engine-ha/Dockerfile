FROM bitnami/java:11.0.21-10
RUN mkdir -p /opt
RUN mkdir -p /opt/config
COPY ./config/* /opt/config
COPY ./com.openi40.scheduler.engine.ha.bootable.jar /opt
WORKDIR /opt
ENTRYPOINT java -Xmx1G -jar /opt/com.openi40.scheduler.engine.ha.bootable.jar
