## 镜像构建
1. docker build -t k8s-registry.banksteel.com/baseplatform/bone-config-server:v1.9 .
2. docker login k8s-registry.banksteel.com -uadmin -pMysteel123
3. docker push k8s-registry.banksteel.com/baseplatform/bone-config-server:v1.9

## 删除k8s对象(慎重)
1. kubectl delete -f config-server-dev.yaml
2. kubectl delete -f config-server-test.yaml
3. kubectl delete -f config-server-pre.yaml
4. kubectl delete -f config-server-prod.yaml

## 创建k8s对象
1. kubectl create -f config-server-dev.yaml
2. kubectl create -f config-server-test.yaml
3. kubectl create -f config-server-pre.yaml
4. kubectl create -f config-server-prod.yaml

## 访问地址
1. 开发环境：http://192.168.200.64:30085/
2. 测试环境：http://192.168.200.64:30086/
3. 预发环境：http://192.168.200.64:30087/
4. 生产环境：http://192.168.200.64:30088/

#生产环境中配置中心的域名
http://bone-config-server-dev.banksteel.com/
http://bone-config-server-test.banksteel.com/
http://bone-config-server-pre.banksteel.com/
http://bone-config-server.banksteel.com/