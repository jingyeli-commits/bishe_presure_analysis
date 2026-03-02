package com.ruoyi.crawler.service;

import com.ruoyi.system.domain.EmploymentData;
import com.ruoyi.system.domain.IndustryDemand;
import com.ruoyi.system.service.IEmploymentDataService;
import com.ruoyi.system.service.IIndustryDemandService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 爬虫服务（真实爬取本地模拟页面）
 *
 * @author ruoyi
 */
@Slf4j
@Service
public class CrawlerService {

    @Autowired
    private IEmploymentDataService employmentDataService;

    @Autowired
    private IIndustryDemandService industryDemandService;

    /**
     * 爬取就业数据（真实爬取本地HTML页面）
     */
    public void crawlEmploymentData() {
        log.info("开始爬取就业数据...");

        try {
            // 构建本地页面URL
            String url = "http://localhost:8080/mock-data/employment-report.html";
            log.info("爬取目标URL: {}", url);

            // 使用Jsoup爬取HTML页面
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .timeout(10000)
                    .get();

            log.info("成功获取HTML页面，开始解析...");

            List<EmploymentData> dataList = new ArrayList<>();

            // 解析每个大学的数据
            Elements universitySections = doc.select(".university-section");
            log.info("找到{}所大学的数据", universitySections.size());

            for (Element section : universitySections) {
                String universityName = section.attr("data-university");
                log.info("  正在解析{}的数据...", universityName);

                // 解析表格数据
                Elements rows = section.select("table.employment-table tbody tr");

                for (Element row : rows) {
                    try {
                        EmploymentData data = new EmploymentData();

                        // 提取各列数据
                        data.setUniversityName(universityName);
                        data.setMajorName(row.select("td.major").text());

                        String graduateCount = row.select("td.graduate-count").text();
                        data.setGraduateCount(Integer.parseInt(graduateCount));

                        String employedCount = row.select("td.employed-count").text();
                        data.setEmployedCount(Integer.parseInt(employedCount));

                        String rate = row.select("td.rate").text();
                        data.setEmploymentRate(new BigDecimal(rate));

                        String salary = row.select("td.salary").text();
                        data.setAverageSalary(new BigDecimal(salary));

                        String matchRate = row.select("td.match-rate").text();
                        data.setMatchRate(new BigDecimal(matchRate));

                        String studyRate = row.select("td.study-rate").text();
                        data.setFurtherStudyRate(new BigDecimal(studyRate));

                        // 设置其他字段
                        data.setGraduationYear(2025);
                        data.setDataSource(url);
                        data.setCrawlTime(new Date());
                        data.setCreateTime(new Date());

                        dataList.add(data);

                    } catch (Exception e) {
                        log.error("解析数据行失败: {}", e.getMessage());
                    }
                }

                log.info("  {}解析完成，共{}条数据", universityName, rows.size());
            }

            // 保存到数据库
            if (!dataList.isEmpty()) {
                employmentDataService.saveEmploymentData(dataList);
                log.info("成功爬取并保存{}条就业数据", dataList.size());
            } else {
                log.warn("未爬取到任何数据");
            }

        } catch (Exception e) {
            log.error("爬取就业数据失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 爬取行业需求数据（真实爬取本地HTML页面）
     */
    public void crawlIndustryData() {
        log.info("开始爬取行业需求数据...");

        try {
            // 构建本地页面URL
            String url = "http://localhost:8080/mock-data/industry-demand.html";
            log.info("爬取目标URL: {}", url);

            // 使用Jsoup爬取HTML页面
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .timeout(10000)
                    .get();

            log.info("成功获取HTML页面，开始解析...");

            List<IndustryDemand> dataList = new ArrayList<>();

            // 解析每个行业的数据
            Elements industrySections = doc.select(".industry-section");
            log.info("找到{}个行业的数据", industrySections.size());

            for (Element section : industrySections) {
                String industryName = section.attr("data-industry");
                log.info("  正在解析{}的数据...", industryName);

                // 解析表格数据
                Elements rows = section.select("table.job-table tbody tr");

                for (Element row : rows) {
                    try {
                        IndustryDemand data = new IndustryDemand();

                        // 提取各列数据
                        data.setIndustryName(industryName);

                        String position = row.select("td.position").text();
                        // 移除"热门"标签
                        position = position.replace("热门", "").trim();
                        data.setPositionName(position);

                        String demand = row.select("td.demand").text();
                        data.setDemandCount(Integer.parseInt(demand));

                        String salaryRange = row.select("td.salary-range").text();
                        data.setSalaryRange(salaryRange);

                        String avgSalary = row.select("td.avg-salary").text();
                        data.setAverageSalary(new BigDecimal(avgSalary));

                        data.setEducationRequirement(row.select("td.education").text());
                        data.setExperienceRequirement(row.select("td.experience").text());
                        data.setLocation(row.select("td.location").text());

                        // 设置其他字段
                        data.setStatisticsYear(2025);
                        data.setStatisticsMonth(6);
                        data.setDataSource(url);
                        data.setCrawlTime(new Date());
                        data.setCreateTime(new Date());

                        dataList.add(data);

                    } catch (Exception e) {
                        log.error("解析数据行失败: {}", e.getMessage());
                    }
                }

                log.info("  {}解析完成，共{}条数据", industryName, rows.size());
            }

            // 保存到数据库
            if (!dataList.isEmpty()) {
                industryDemandService.saveBatch(dataList);
                log.info("成功爬取并保存{}条行业需求数据", dataList.size());
            } else {
                log.warn("未爬取到任何数据");
            }

        } catch (Exception e) {
            log.error("爬取行业需求数据失败: {}", e.getMessage(), e);
        }
    }
}