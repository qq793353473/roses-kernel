# roses-kernel 

---
   
## 介绍
本项目为Roses系列微服务框架的模块之一，Roses基于`Spring Boot 2`和`Spring Cloud Finchley.RELEASE`，致力做更简洁的**分布式**和**服务化**解决方案，Roses拥有高效率的开发体验，提供可靠消息最终一致性分布式事务解决方案，提供基于调用链的服务治理，提供可靠的服务异常定位方案（Log + Trace）等等，**一个分布式框架不仅需要构建高效稳定的底层开发框架，更需要解决分布式带来的种种挑战**，请关注Roses微服务框架[https://gitee.com/naan1993/roses](https://gitee.com/naan1993/roses)

---

## 本项目模块介绍

| 模块名称 | 说明 | 端口 | 备注 |
| :---: | :---: | :---: | :---: |
| kernel-actuator | actuator监控 | 无 | 无 |
| kernel-core | 核心包 | 无 | 自动配置和工具类 |
| kernel-generator | 代码生成 | 无 | 根据数据库生成实体，dao，service等 |
| kernel-jwt | jwt模块 | 无 | 实现jwt鉴权 |
| kernel-logger | 日志记录 | 无 | 日志记录工具类 |
| kernel-model | 通用实体 | 无 | 通用实体，接口，枚举，异常规范等 |
| kernel-scanner | 资源扫描 | 无 | 统一搜集各个服务的接口 |
| kernel-sign | 签名模块 | 无 | 实现数据签名 |
| kernel-validator | 校验器 | 无 | 参数校验工具类 |

---

## 注意事项

> * 开发环境为jdk 1.8
> * maven推荐使用阿里云镜像，拉取jar包保证成功

---

## 项目特点

业务服务的核心，开发微服务的利器，包含自动配置，工具类，常用model等
