# 大连民族软件工程 大二数据库课程设计
> 写在前面  现在是做完课设快一个多月了吧  成绩已经登出了  这篇课设说实话 我是为了第一个验收(高分) 所以只是一个非常简单的CRUD项目  Service层写的不好 逻辑几乎没有 应该考虑各种情况才对!
> 然后呢 全局异常捕捉类也写的不好 都没有定义  然后我的数据库sql可能有一点错误 因为老师后来提出要求进行更新时间 创建时间的 字段 当时我已经验收完了 我就简单的把sql语句改了一下  
> 更新时间和创建时间大家可以用aop切面 来进行实现  还有一个接口加密的功能 这个功能 也可以用aop
> 大家拉我的倒数第二个提交就行了!


## 本次课设使用的软件
**前端开发:webstorm**
**后端开发:Idea**
**服务器连接:Navicat Premium 16**
**接口测试:ApiFox**
**写日报工具:Vscode+Markdown插件**
## 前端
**使用组件：node + npm + vue + yarn + router/pinia + vuex +axios + element plus**
**使用框架：vue3 + element-plus**
## 后端
**开发语言：java**
**使用框架：SpringBoot3 + Mybatis  + SpringSecurity**
**包管理:maven**

## 服务器
**Linux版本：CentOS 7.9**
**数据库：Mysql + Redis**
## 服务器管理工具
**宝塔面板**
## 代码管理
**使用工具：Git + GitHub**

# 软件架构
DBbackend 为后端  frontend为前端
## DBbackend
+ aop层 切面编程 声明自定义注解
+ aspect层 切面 负责实现接口加密
+ common为公共类  里面有个统一结果类
+ config层 前端登录鉴权与拦截器
+ controller层 与前端交互
+ entity层 实体类
+ filter层 springsecurity的登录拦截
+ mapper层 数据库操作相关
+ service层 分为seriver接口和实现类
+ util层 工具类

## frontend
frontend中有一个VUE3项目有一个个src文件。
+ src中App为前端入口
+ router为前端的路由设置文件
+ api层 负责发送各种请求 调用后端接口
+ net层 封装请求层
+ style为前端的风格
+ views层 存放背景图和欢迎界面
+ componesnt层 导航栏及侧边栏组件
+ components层 前端注册界面
+ stores 负责获取登录人的信息 来实现页面跳转
+ page层 公司/港口/入库/出库界面
+ main.js为前端的挂载函数

## sql.rar
为sql创建脚本

