# HertzBeat-Lite

参考 Apache HertzBeat 的个人后端学习项目，使用 Java / Spring Boot，逐步实现 HTTP 接口监控、故障告警、可靠通知与 AI 辅助排障。采用独立实现的精简设计，非 Apache 官方项目。

## 当前已完成

- Spring Boot 基础工程：启动入口、Maven 构建与基础配置。
- 系统信息接口 `GET /api/system/info`：读取配置中的应用名称与展示版本，返回 JSON。
- Controller、Service、响应 DTO 分层，使用构造器注入与 Java record。

监控目标 CRUD、定时探测、告警、中间件与 AI 工具调用是后续开发计划，尚未实现。

## 技术与环境

- JDK 21
- Maven 3.9.x
- Spring Boot 3.5.13 / Spring Web

当前阶段不依赖数据库、Redis、消息队列或模型 API Key。

## 启动与访问

在项目根目录执行：

```powershell
mvn spring-boot:run
```

也可以在 IntelliJ IDEA 中导入 `pom.xml`，设置 JDK 21，运行 `HertzBeatLiteApplication`。选择一种方式启动，避免端口冲突。

在另一个终端访问：

```powershell
curl.exe -i http://localhost:8080/api/system/info
```

预期返回 HTTP 200：

```json
{
  "name": "HertzBeat-Lite",
  "version": "0.0.1"
}
```

名称与展示版本来自 `application.yml` 的 `app.name`、`app.version`；修改后重启生效。展示版本由配置维护，并非自动读取 Git 提交号。

## 代码职责

```text
src/main/
├── java/com/cwtspring/hertzbeatlite/
│   ├── HertzBeatLiteApplication.java
│   └── system/
│       ├── controller/SystemInfoController.java
│       ├── service/SystemInfoService.java
│       └── dto/SystemInfoResponse.java
└── resources/application.yml
```

- `SystemInfoController`：接收查询请求，调用 Service，返回响应对象。
- `SystemInfoService`：通过构造器接收名称与版本配置，组装响应数据。
- `SystemInfoResponse`：定义 `name`、`version` 两个响应字段，由 Spring MVC 转换成 JSON。

系统信息接口供平台信息展示使用，不代表数据库或被监控服务的健康状态。

## 构建与验证

```powershell
mvn clean verify
java -jar target/hertzbeat-lite-0.0.1-SNAPSHOT.jar
```

当前尚未编写自动化测试，构建成功不等于测试套件通过。接口验收包含：GET 系统信息返回 200、POST 同一路径返回 405、未知路径返回 404，以及修改展示版本并重启后的返回值验证。

## 提交范围与敏感信息

- 本仓库提交业务代码、构建文件、公共配置与说明文档。
- `demo` 为本地教学示例，已通过 `.gitignore` 排除；`target`、IDE 配置与日志也不提交。
- 真实 API Key、数据库账号密码、令牌和私钥不要写入 Java 源码或公共配置。
- 后续优先通过环境变量注入敏感值；公共配置仅引用占位符，例如 `${DB_USERNAME}`、`${DB_PASSWORD}`、`${MODEL_API_KEY}`。
- 如需本地配置，可使用已忽略的 `application-local.yml`，启动时显式激活 `local` profile。Spring Boot 默认不会自动读取 `.env`，使用它时需额外配置加载方式。
- `.gitignore` 不会自动清除已跟踪文件中的秘密；每次提交前仍需检查暂存差异。如果真实凭据已公开，应立即撤销或轮换，不能仅靠删除文件处理。

## 参考

- Apache HertzBeat： https://github.com/apache/hertzbeat
