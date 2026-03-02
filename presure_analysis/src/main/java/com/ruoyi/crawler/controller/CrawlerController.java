package com.ruoyi.crawler.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.crawler.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 爬虫控制器
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/crawler")
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    /**
     * 手动触发就业数据爬取
     */
    @PostMapping("/employment/start")
    public AjaxResult startEmploymentCrawler() {
        try {
            crawlerService.crawlEmploymentData();
            return AjaxResult.success("就业数据爬取任务已启动");
        } catch (Exception e) {
            return AjaxResult.error("爬取失败: " + e.getMessage());
        }
    }

    /**
     * 手动触发行业需求数据爬取
     */
    @PostMapping("/industry/start")
    public AjaxResult startIndustryCrawler() {
        try {
            crawlerService.crawlIndustryData();
            return AjaxResult.success("行业需求数据爬取任务已启动");
        } catch (Exception e) {
            return AjaxResult.error("爬取失败: " + e.getMessage());
        }
    }
}