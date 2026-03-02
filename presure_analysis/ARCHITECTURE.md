# 系统架构说明

## 📐 系统架构

### 整体架构
```
┌─────────────────────────────────────────────────┐
│               前端展示层 (Presentation)          │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │  首页    │  │数据分析  │  │数据管理  │     │
│  └──────────┘  └──────────┘  └──────────┘     │
│        Thymeleaf + ECharts + Axios             │
└─────────────────────────────────────────────────┘
                      ↕ HTTP/REST
┌─────────────────────────────────────────────────┐
│            业务逻辑层 (Business Logic)           │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │Controller│  │ Service  │  │ Mapper   │     │
│  └──────────┘  └──────────┘  └──────────┘     │
│        Spring Boot + MyBatis Plus              │
└─────────────────────────────────────────────────┘
                      ↕ JDBC
┌─────────────────────────────────────────────────┐
│              数据持久层 (Data Access)            │
│                   MySQL Database                │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │就业数据  │  │行业需求  │  │压力指标  │     │
│  └──────────┘  └──────────┘  └──────────┘     │
└─────────────────────────────────────────────────┘
                      ↕
┌─────────────────────────────────────────────────┐
│             数据采集层 (Data Collection)         │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐     │
│  │网络爬虫  │  │定时任务  │  │数据处理  │     │
│  └──────────┘  └──────────┘  └──────────┘     │
│            Jsoup + HttpClient + Quartz         │
└─────────────────────────────────────────────────┘
```

## 🏗️ 技术架构

### 1. 表现层 (Presentation Layer)
- **技术栈**: Thymeleaf, HTML5, CSS3, JavaScript
- **可视化**: ECharts 5.x
- **HTTP客户端**: Axios
- **职责**:
  - 页面渲染
  - 用户交互
  - 数据可视化
  - 前端路由

### 2. 控制层 (Controller Layer)
- **框架**: Spring MVC
- **注解**: @RestController, @Controller
- **职责**:
  - 接收HTTP请求
  - 参数验证
  - 调用Service
  - 返回响应

**主要Controller**:
- `IndexController`: 页面跳转
- `EmploymentDataController`: 就业数据接口
- `IndustryDemandController`: 行业需求接口
- `EmploymentPressureController`: 就业压力接口
- `CrawlerController`: 爬虫控制接口

### 3. 业务层 (Service Layer)
- **框架**: Spring Service
- **事务管理**: @Transactional
- **职责**:
  - 业务逻辑处理
  - 事务管理
  - 数据转换
  - 统计计算

**主要Service**:
- `IEmploymentDataService`: 就业数据业务
- `IIndustryDemandService`: 行业需求业务
- `IEmploymentPressureService`: 就业压力业务
- `CrawlerService`: 爬虫业务

### 4. 持久层 (Persistence Layer)
- **ORM框架**: MyBatis Plus
- **数据库**: MySQL 8.0
- **连接池**: Druid
- **职责**:
  - 数据库操作
  - SQL映射
  - 结果映射
  - 查询优化

**主要Mapper**:
- `EmploymentDataMapper`: 就业数据映射
- `IndustryDemandMapper`: 行业需求映射
- `EmploymentPressureMapper`: 就业压力映射

### 5. 数据采集层 (Crawler Layer)
- **爬虫框架**: Jsoup
- **HTTP客户端**: Apache HttpClient
- **定时任务**: Spring Quartz
- **职责**:
  - 网页抓取
  - 数据解析
  - 数据清洗
  - 定时采集

## 📊 数据流程

### 数据采集流程
```
网络爬虫 → 数据解析 → 数据清洗 → 数据存储 → 数据展示
   ↓          ↓          ↓          ↓          ↓
Jsoup → DOM解析 → 格式化 → MySQL → ECharts
```

### 请求处理流程
```
用户请求 → Controller → Service → Mapper → Database
                                              ↓
用户响应 ← View ← Controller ← Service ← 结果集
```

## 🗄️ 数据库设计

### E-R图概览
```
┌──────────────┐         ┌──────────────┐
│EmploymentData│         │IndustryDemand│
├──────────────┤         ├──────────────┤
│ id (PK)      │         │ id (PK)      │
│ university   │         │ industry     │
│ major        │         │ position     │
│ year         │         │ demand_count │
│ rate         │         │ salary       │
└──────────────┘         └──────────────┘
       ↓                        ↓
       └────────────┬───────────┘
                    ↓
         ┌──────────────────┐
         │EmploymentPressure│
         ├──────────────────┤
         │ id (PK)          │
         │ major            │
         │ year             │
         │ pressure_index   │
         └──────────────────┘
```

### 表关系
- **employment_data**: 存储就业基础数据
- **industry_demand**: 存储行业需求数据
- **employment_pressure**: 存储就业压力指标

各表通过专业名称(major)和年份(year)进行关联查询。

## 🔄 核心流程

### 1. 爬虫数据采集流程
```java
定时任务触发
    ↓
CrawlerService.crawlEmploymentData()
    ↓
生成/爬取数据
    ↓
数据校验和清洗
    ↓
批量保存到数据库
    ↓
记录日志
```

### 2. 数据查询展示流程
```java
用户访问页面
    ↓
Controller接收请求
    ↓
Service处理业务逻辑
    ↓
Mapper查询数据库
    ↓
数据聚合和计算
    ↓
返回JSON数据
    ↓
前端ECharts渲染图表
```

### 3. 统计分析流程
```java
前端请求统计数据
    ↓
Controller调用Service
    ↓
Service执行复杂SQL
    ↓
聚合函数计算(AVG, SUM, COUNT)
    ↓
格式化返回结果
    ↓
前端展示统计卡片
```

## 🎯 设计模式

### 1. MVC模式
- Model: 实体类(Domain)
- View: Thymeleaf模板
- Controller: Spring Controller

### 2. 三层架构
- Controller层: 请求处理
- Service层: 业务逻辑
- Mapper层: 数据访问

### 3. 依赖注入
- 使用@Autowired注入依赖
- 降低耦合度
- 便于测试和维护

### 4. RESTful API
- 统一接口规范
- 使用HTTP方法(GET/POST)
- 返回JSON格式数据

## 🔒 安全设计

### 1. SQL注入防护
- 使用MyBatis Plus参数化查询
- 避免拼接SQL

### 2. XSS防护
- 前端输入验证
- 后端数据过滤

### 3. 连接池管理
- Druid连接池
- 防止资源耗尽

## ⚡ 性能优化

### 1. 数据库优化
- 添加索引(major, year, university)
- 使用连接池
- 合理设计表结构

### 2. 缓存策略
- 统计数据可添加缓存
- 减少数据库查询

### 3. 异步处理
- 爬虫任务异步执行
- 定时任务后台运行

### 4. 前端优化
- 图表懒加载
- 响应式设计
- 资源压缩

## 🔮 扩展性

### 可扩展点
1. **数据源扩展**: 支持更多数据来源
2. **分析模型扩展**: 添加更多分析维度
3. **可视化扩展**: 支持更多图表类型
4. **导出功能**: 支持Excel/PDF导出
5. **用户系统**: 添加用户登录和权限管理
6. **实时分析**: 添加实时数据分析功能

### 微服务改造
系统可以拆分为:
- 爬虫服务
- 数据分析服务
- 可视化服务
- API网关

## 📈 监控和运维

### 日志管理
- 使用SLF4J + Logback
- 日志级别分离
- 日志文件按日期滚动

### 健康检查
- Spring Boot Actuator
- 数据库连接检测
- 爬虫任务监控

## 🎓 学习价值

本项目适合学习:
1. Spring Boot开发
2. MyBatis Plus使用
3. 网络爬虫技术
4. 数据可视化
5. 前后端交互
6. 系统架构设计
7. 若依框架理解

---

**持续更新中...**
