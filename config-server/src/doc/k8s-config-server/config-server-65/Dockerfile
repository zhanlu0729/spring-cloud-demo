FROM k8s-registry.banksteel.com/base/jdk8:v1
MAINTAINER ZhouJia <zhoujia@banksteel.com>
ADD bone-cloud-config-server.jar /opt/bone-cloud-config-server.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /opt/bone-cloud-config-server.jar