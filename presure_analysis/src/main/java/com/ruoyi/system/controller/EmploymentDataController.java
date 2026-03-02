package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.EmploymentData;
import com.ruoyi.system.service.IEmploymentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 就业数据Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/employment")
public class EmploymentDataController {

    @Autowired
    private IEmploymentDataService employmentDataService;

    /**
     * 查询就业数据列表
     */
    @GetMapping("/list")
    public AjaxResult list(EmploymentData employmentData) {
        List<EmploymentData> list = employmentDataService.selectEmploymentDataList(employmentData);
        return AjaxResult.success(list);
    }

    /**
     * 获取就业数据详情
     */
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable Long id) {
        return AjaxResult.success(employmentDataService.getById(id));
    }

    /**
     * 按年份统计就业率
     */
    @GetMapping("/rate/year")
    public AjaxResult getEmploymentRateByYear() {
        List<Map<String, Object>> data = employmentDataService.getEmploymentRateByYear();
        return AjaxResult.success(data);
    }

    /**
     * 按专业统计平均薪资（TOP10）
     */
    @GetMapping("/salary/major")
    public AjaxResult getTopSalaryByMajor() {
        List<Map<String, Object>> data = employmentDataService.getTopSalaryByMajor();
        return AjaxResult.success(data);
    }

    /**
     * 按大学统计就业率
     */
    @GetMapping("/rate/university")
    public AjaxResult getEmploymentRateByUniversity(@RequestParam(defaultValue = "2025") Integer year) {
        List<Map<String, Object>> data = employmentDataService.getEmploymentRateByUniversity(year);
        return AjaxResult.success(data);
    }

    /**
     * 统计各专业毕业生人数
     */
    @GetMapping("/count/major")
    public AjaxResult getGraduateCountByMajor(@RequestParam(defaultValue = "2025") Integer year) {
        List<Map<String, Object>> data = employmentDataService.getGraduateCountByMajor(year);
        return AjaxResult.success(data);
    }

    /**
     * 获取综合统计数据
     */
    @GetMapping("/statistics")
    public AjaxResult getStatistics() {
        Map<String, Object> result = new HashMap<>();
        
        // 总数据量
        long totalCount = employmentDataService.count();
        result.put("totalCount", totalCount);
        
        // 平均就业率
        List<EmploymentData> allData = employmentDataService.list();
        double avgRate = allData.stream()
                .mapToDouble(d -> d.getEmploymentRate().doubleValue())
                .average()
                .orElse(0.0);
        result.put("avgEmploymentRate", String.format("%.2f", avgRate));
        
        // 平均薪资
        double avgSalary = allData.stream()
                .mapToDouble(d -> d.getAverageSalary().doubleValue())
                .average()
                .orElse(0.0);
        result.put("avgSalary", String.format("%.2f", avgSalary));
        
        // 总毕业生人数
        int totalGraduates = allData.stream()
                .mapToInt(EmploymentData::getGraduateCount)
                .sum();
        result.put("totalGraduates", totalGraduates);
        
        return AjaxResult.success(result);
    }
}
