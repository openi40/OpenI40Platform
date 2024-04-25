FROM bitnami/java:11.0.21-10
RUN mkdir -p /opt
COPY ../target/com.openi40.persistence.server.app.bootable.jar /opt
RUN java -Xmx1G -jar /opt/com.openi40.persistence.server.app.bootable.jar