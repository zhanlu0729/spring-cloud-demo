FROM k8s-registry.xxx.com/base/jdk:v1.8
MAINTAINER ZhouJia <zhoujia@banksteel.com>
ADD config-server.jar /opt/config-server.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /opt/config-server.jar