## Actuator 端点信息

| 端点路径 |  请求类型  |  描述             |
| --------- | ------------| ------------ |
| /autoconfig    |  GET  | 查看自动配置的使用情况 |
| /configprops    |  GET   | 查看配置属性，包括默认配置 |
| /beans   |  GET | bean及其关系列表  |
| /dump   |  GET    | 打印线程堆栈  |
| /env   |  GET     | 查看所有环境变量   |
| /env/{name}    |  GET    | 查看具体变量值   |
| /health    |  GET  | 查看应用健康指标 |
| /info    |  GET   | 查看应用信息 |
| /mappings   |  GET | 查看所有URL映射  |
| /metrics    |  GET   | 查看应用基本指标  |
| /metrics/{name}    |  GET   | 查看具体指标  |
| /shutdown    |  POST    | 关闭应用   |
| /trace     |  GET   | 查看基本追踪信息  |
