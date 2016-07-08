# c2工作流

c2工作流基于开源Activiti引擎，用来处理业务系统运行过程中发起的业务流程，我有以下几大功能：

1. 内核基于bpmn2规范实现，bpmn2规范包含的功能都能实现。
1. BS/CS方式可视化业务流程建模。
2. 流程管理控制台。
3. 引擎支持可分布式部署也可以独立运行，统一的接口服务调用，分布式下支持多租户数据隔离。
4. 实现了一些中国特色的流程场景，比如：驳回、自由流、顺序会签等。
5. 工作流任务可以分配到人，也可以分配到虚拟组。


## 安装(依赖)

我的最新版本是4.1.6，组件Maven坐标：

```
	<dependency>
	    <groupId>com.chinacreator.c2</groupId>
	    <artifactId>c2-flow-ui</artifactId>
	    <version>4.1.6</version>
	</dependency>
```

[点击查看升级日志](changelog.md)

## 使用

请参考[c2工作流使用指南](http://docs.c2cloud.cn/#/f/docViewer?doc=c2-flow)

## 贡献代码

如果对该项目有任何意见和建议，或发现了BUG，请在项目的[Issues页面](../../issues)提交`issue`，我们会尽快回复。

如果您希望帮助我们一起改进，欢迎fork本项目，并将您的修改以Merge Reuqest的方式合并到我们的仓库，具体操作请参考[研发中心项目Merge Request提交指南](https://git.c2cloud.cn/c2/guideline/wikis/contributing-guideline)

## 许可

本项目是一个科创内部开源的项目，遵守[科创开源项目通用许可C2PL](https://git.c2cloud.cn/c2/guideline/blob/master/license.md)

