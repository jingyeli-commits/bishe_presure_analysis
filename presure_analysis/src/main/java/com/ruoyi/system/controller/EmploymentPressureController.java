package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IEmploymentPressureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 就业压力Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/pressure")
public class EmploymentPressureController {

    @Autowired
    private IEmploymentPressureService employmentPressureService;

    /**
     * 查询压力指数最高的专业
     */
    @GetMapping("/top/majors")
    public AjaxResult getTopPressureMajors(@RequestParam(defaultValue = "2025") Integer year) {
        List<Map<String, Object>> data = employmentPressureService.getTopPressureMajors(year);
        return AjaxResult.success(data);
    }

    /**
     * 按年份统计平均压力指数
     */
    @GetMapping("/avg/year")
    public AjaxResult getAvgPressureByYear() {
        List<Map<String, Object>> data = employmentPressureService.getAvgPressureByYear();
        return AjaxResult.success(data);
    }

    /**
     * 查询专业压力趋势
     */
    @GetMapping("/trend/major")
    public AjaxResult getPressureTrendByMajor(@RequestParam String majorName) {
        List<Map<String, Object>> data = employmentPressureService.getPressureTrendByMajor(majorName);
        return AjaxResult.success(data);
    }
}
