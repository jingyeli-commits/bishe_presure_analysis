package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * @author ruoyi
 */
@SpringBootApplication
@MapperScan("com.ruoyi.**.mapper")
public class EmploymentAnalysisApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EmploymentAnalysisApplication.class, args);
        System.out.println("====================================");
        System.out.println("  高校毕业生就业压力分析系统启动成功");
        System.out.println("  访问地址: http://localhost:8080");
        System.out.println("====================================");
    }
}
