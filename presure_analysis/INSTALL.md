# 快速安装指南

## 📋 前置条件

### 必需软件
1. **JDK 1.8+** 
   - 下载地址: https://www.oracle.com/java/technologies/downloads/
   - 安装后配置JAVA_HOME环境变量

2. **Maven 3.6+**
   - 下载地址: https://maven.apache.org/download.cgi
   - 安装后配置MAVEN_HOME和PATH环境变量

3. **MySQL 8.0+**
   - 下载地址: https://dev.mysql.com/downloads/mysql/
   - 安装时记住root密码

4. **IDE (可选)**
   - IntelliJ IDEA (推荐)
   - Eclipse
   - VS Code + Java Extension Pack

## 🚀 安装步骤

### 步骤1: 配置数据库

1. 启动MySQL服务
2. 创建数据库并导入数据

**方法A: 使用MySQL命令行**
```bash
mysql -u root -p
```
然后在MySQL命令行中执行:
```sql
source /path/to/employment-analysis-system/database.sql
```

**方法B: 使用MySQL Workbench或Navicat**
- 打开database.sql文件
- 执行整个脚本

### 步骤2: 修改配置文件

编辑 `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/employment_analysis?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8
    username: root      # 改成你的MySQL用户名
    password: 123456    # 改成你的MySQL密码
```

### 步骤3: 编译运行

**方法A: 使用启动脚本 (推荐)**

Windows:
```bash
双击 start.bat
```

Linux/Mac:
```bash
./start.sh
```

**方法B: 使用Maven命令**
```bash
# 清理编译
mvn clean package

# 运行
java -jar target/employment-analysis.jar
```

**方法C: 使用IDE**
1. 用IDEA打开项目文件夹
2. 等待Maven依赖下载完成
3. 找到 `EmploymentAnalysisApplication.java`
4. 右键 -> Run 'EmploymentAnalysisApplication'

### 步骤4: 访问系统

打开浏览器访问: **http://localhost:8080**

## ✅ 验证安装

访问系统后，你应该能看到:
1. ✅ 首页显示统计卡片
2. ✅ 图表正常渲染
3. ✅ 导航栏可以跳转
4. ✅ 点击"爬取数据"按钮可以生成数据

## ❗ 常见问题

### 1. 端口被占用
```
Error: Port 8080 is already in use
```
**解决方案**: 修改 `application.yml` 中的端口号
```yaml
server:
  port: 8081  # 改成其他端口
```

### 2. 数据库连接失败
```
Error: Could not connect to database
```
**解决方案**:
- 检查MySQL是否启动
- 检查用户名密码是否正确
- 检查数据库名称是否为 `employment_analysis`

### 3. Maven依赖下载慢
**解决方案**: 配置国内Maven镜像

编辑 `~/.m2/settings.xml` (没有就创建):
```xml
<mirrors>
  <mirror>
    <id>aliyun</id>
    <mirrorOf>central</mirrorOf>
    <name>Aliyun Maven</name>
    <url>https://maven.aliyun.com/repository/public</url>
  </mirror>
</mirrors>
```

### 4. 启动后无法访问
**解决方案**:
- 检查防火墙设置
- 确认启动日志中没有错误
- 尝试访问 http://127.0.0.1:8080

## 📞 获取帮助

如果遇到其他问题:
1. 查看控制台日志输出
2. 检查 `logs/` 目录下的日志文件
3. 在GitHub上提Issue

## 🎉 开始使用

安装成功后，你可以:
- 📊 查看首页的数据统计和图表
- 🕷️ 点击"爬取数据"按钮生成模拟数据
- 📈 浏览不同的数据分析页面
- 🔍 探索系统的各项功能

祝使用愉快！
