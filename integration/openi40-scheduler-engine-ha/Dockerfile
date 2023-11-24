FROM bitnami/java:11.0.21-10
RUN mkdir -p /opt
COPY ./com.openi40.scheduler.engine.ha.bootable.jar /opt
CMD java -Xmx500m -jar /opt/com.openi40.scheduler.engine.ha.bootable.jar