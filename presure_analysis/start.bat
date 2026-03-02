@echo off
echo ================================
echo  高校毕业生就业压力分析系统
echo ================================
echo.

echo [1] 正在检查Java环境...
java -version
if %errorlevel% neq 0 (
    echo 错误: 未找到Java环境，请先安装JDK 1.8+
    pause
    exit
)

echo.
echo [2] 正在编译项目...
call mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo 错误: 编译失败
    pause
    exit
)

echo.
echo [3] 正在启动应用...
echo.
echo ================================
echo  访问地址: http://localhost:8080
echo ================================
echo.

java -jar target/employment-analysis.jar

pause
