# 高校毕业生就业压力分析系统

## 📚 项目简介

基于若依(RuoYi)架构的高校毕业生就业压力分析系统，采用Spring Boot + MyBatis Plus + MySQL + Thymeleaf + ECharts技术栈，
集成网络爬虫技术实现就业数据的自动采集与分析。

## ✨ 主要功能

### 1. 数据采集模块
- 🕷️ 网络爬虫自动采集就业数据
- 📊 定时任务自动更新数据
- 🔄 手动触发数据采集功能

### 2. 就业数据分析
- 📈 历年就业率趋势分析
- 💰 各专业平均薪资统计
- 🏫 各大学就业率对比
- 👥 各专业毕业生人数统计

### 3. 行业需求分析
- 🏭 各行业人才需求量分析
- 📍 各地区招聘需求统计
- 🔥 热门职位排行

### 4. 就业压力分析
- 📉 就业压力指数统计
- 🎯 专业竞争比率分析
- ⏱️ 平均求职时长统计

### 5. 数据可视化
- 📊 折线图展示趋势
- 📈 柱状图对比数据
- 🥧 饼图呈现分布
- 🎨 交互式图表展示

## 🛠️ 技术栈

### 后端
- **框架**: Spring Boot 2.7.14
- **ORM**: MyBatis Plus 3.5.3.1
- **数据库**: MySQL 8.0
- **连接池**: Druid 1.2.18
- **爬虫**: Jsoup 1.15.4, HttpClient 4.5.14
- **JSON**: FastJSON 1.2.83

### 前端
- **模板引擎**: Thymeleaf
- **图表库**: ECharts 5.4.3
- **HTTP客户端**: Axios
- **样式**: 自定义CSS

## 📁 项目结构

```
employment-analysis-system/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── ruoyi/
│       │           ├── EmploymentAnalysisApplication.java  # 启动类
│       │           ├── common/                    # 公共模块
│       │           │   └── core/
│       │           │       └── domain/
│       │           │           └── AjaxResult.java    # 统一响应类
│       │           ├── system/                    # 系统模块
│       │           │   ├── domain/               # 实体类
│       │           │   │   ├── EmploymentData.java
│       │           │   │   ├── IndustryDemand.java
│       │           │   │   └── EmploymentPressure.java
│       │           │   ├── mapper/               # Mapper接口
│       │           │   ├── service/              # Service层
│       │           │   └── controller/           # Controller层
│       │           └── crawler/                  # 爬虫模块
│       │               ├── service/
│       │               │   └── CrawlerService.java
│       │               ├── task/
│       │               │   └── CrawlerTask.java
│       │               └── controller/
│       │                   └── CrawlerController.java
│       └── resources/
│           ├── application.yml               # 配置文件
│           ├── static/                      # 静态资源
│           │   ├── css/
│           │   │   └── style.css
│           │   └── js/
│           │       └── index.js
│           └── templates/                   # 模板文件
│               ├── index.html
│               ├── employment/
│               │   └── analysis.html
│               └── industry/
│                   └── analysis.html
├── pom.xml                                  # Maven配置
├── database.sql                             # 数据库脚本
└── README.md                               # 项目文档
```

## 🚀 快速开始

### 1. 环境要求
- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- IDE (推荐IntelliJ IDEA)

### 2. 数据库配置

#### 创建数据库并导入数据
```bash
# 登录MySQL
mysql -u root -p

# 执行SQL脚本
source /path/to/database.sql
```

或者直接在MySQL客户端中执行 `database.sql` 文件

#### 修改数据库连接配置
编辑 `src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/employment_analysis?useUnicode=true&characterEncoding=utf8
    username: root          # 修改为你的MySQL用户名
    password: 123456        # 修改为你的MySQL密码
```

### 3. 编译运行

#### 使用Maven命令
```bash
# 进入项目目录
cd employment-analysis-system

# 清理编译
mvn clean package

# 运行项目
java -jar target/employment-analysis.jar
```

#### 使用IDE运行
1. 用IDEA打开项目
2. 等待Maven依赖下载完成
3. 运行 `EmploymentAnalysisApplication.java` 主类

### 4. 访问系统

浏览器访问: **http://localhost:8080**

## 📊 功能展示

### 首页
- 展示核心统计数据
- 多维度图表分析
- 快速操作入口

### 就业数据分析
- 历年就业率趋势
- 专业薪资排行
- 对口就业率统计

### 行业需求分析
- 行业人才需求分布
- 地区招聘需求对比
- 热门职位排行

### 数据管理
- 手动触发爬虫
- 数据查询与导出

## 🔧 配置说明

### 爬虫配置
在 `application.yml` 中配置爬虫参数:
```yaml
crawler:
  interval: 3000        # 爬虫间隔时间(毫秒)
  timeout: 10000        # 超时时间(毫秒)
  retry: 3              # 重试次数
  user-agent: Mozilla/5.0...
```

### 定时任务
系统默认配置了两个定时任务:
- 每天凌晨2点: 执行就业数据爬取
- 每天凌晨3点: 执行行业需求数据爬取

可在 `CrawlerTask.java` 中修改定时任务配置。

## 📝 API接口

### 就业数据接口
- `GET /system/employment/list` - 查询就业数据列表
- `GET /system/employment/rate/year` - 按年份统计就业率
- `GET /system/employment/salary/major` - 按专业统计薪资
- `GET /system/employment/statistics` - 获取综合统计

### 行业需求接口
- `GET /system/industry/demand/industry` - 按行业统计需求
- `GET /system/industry/demand/location` - 按地区统计需求
- `GET /system/industry/hot/positions` - 热门职位统计

### 爬虫控制接口
- `POST /crawler/employment/start` - 手动触发就业数据爬取
- `POST /crawler/industry/start` - 手动触发行业数据爬取

## 📌 数据库表结构

### employment_data (就业数据表)
- 存储高校就业相关数据
- 包含就业率、薪资、对口率等指标

### industry_demand (行业需求表)
- 存储各行业招聘需求数据
- 包含岗位、薪资、地区等信息

### employment_pressure (就业压力表)
- 存储就业压力相关指标
- 包含压力指数、竞争比率等

## 🔍 爬虫说明

系统集成了基于Jsoup的网络爬虫功能，可以从指定网站采集就业数据。

**注意**: 
- 当前版本使用模拟数据生成方式
- 实际使用时需要根据目标网站调整爬虫规则
- 爬取数据时请遵守网站的robots.txt协议

### 自定义爬虫
在 `CrawlerService.java` 中的 `fetchDocument()` 方法可以实现真实网站爬取:
```java
public Document fetchDocument(String url) throws Exception {
    return Jsoup.connect(url)
            .userAgent(userAgent)
            .timeout(timeout)
            .get();
}
```

## 🤝 贡献

欢迎提交Issue和Pull Request!

## 📄 许可证

MIT License

## 👨‍💻 作者

基于若依框架开发

## 📧 联系方式

如有问题，欢迎通过Issue联系

---

**祝使用愉快！** 🎉
