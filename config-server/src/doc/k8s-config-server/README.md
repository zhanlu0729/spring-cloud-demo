## 镜像构建
1. docker build -t k8s-registry.xxx.com/baseplatform/config-server:v1.9 .
2. docker login k8s-registry.xxx.com -uadmin -pMysteel123
3. docker push k8s-registry.xxx.com/baseplatform/config-server:v1.9

## 删除k8s对象(慎重)
1. kubectl delete -f config-server.yaml

## 创建k8s对象
1. kubectl create -f config-server.yaml