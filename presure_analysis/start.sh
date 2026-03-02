#!/bin/bash

echo "================================"
echo " 高校毕业生就业压力分析系统"
echo "================================"
echo ""

echo "[1] 正在检查Java环境..."
if ! command -v java &> /dev/null; then
    echo "错误: 未找到Java环境，请先安装JDK 1.8+"
    exit 1
fi
java -version

echo ""
echo "[2] 正在编译项目..."
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "错误: 编译失败"
    exit 1
fi

echo ""
echo "[3] 正在启动应用..."
echo ""
echo "================================"
echo " 访问地址: http://localhost:8080"
echo "================================"
echo ""

java -jar target/employment-analysis.jar
