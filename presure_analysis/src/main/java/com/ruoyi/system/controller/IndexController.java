package com.ruoyi.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转Controller
 * 
 * @author ruoyi
 */
@Controller
public class IndexController {

    /**
     * 首页
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 就业数据分析页面
     */
    @GetMapping("/employment/analysis")
    public String employmentAnalysis() {
        return "employment/analysis";
    }

    /**
     * 行业需求分析页面
     */
    @GetMapping("/industry/analysis")
    public String industryAnalysis() {
        return "industry/analysis";
    }

    /**
     * 就业压力分析页面
     */
    @GetMapping("/pressure/analysis")
    public String pressureAnalysis() {
        return "pressure/analysis";
    }

    /**
     * 数据管理页面
     */
    @GetMapping("/data/manage")
    public String dataManage() {
        return "data/manage";
    }
}
