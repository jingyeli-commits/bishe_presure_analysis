package com.ruoyi.system.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IIndustryDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 行业需求Controller
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/industry")
public class IndustryDemandController {

    @Autowired
    private IIndustryDemandService industryDemandService;

    /**
     * 按行业统计需求人数
     */
    @GetMapping("/demand/industry")
    public AjaxResult getDemandByIndustry(@RequestParam(defaultValue = "2025") Integer year) {
        List<Map<String, Object>> data = industryDemandService.getDemandByIndustry(year);
        return AjaxResult.success(data);
    }

    /**
     * 按地区统计需求人数
     */
    @GetMapping("/demand/location")
    public AjaxResult getDemandByLocation(@RequestParam(defaultValue = "2025") Integer year) {
        List<Map<String, Object>> data = industryDemandService.getDemandByLocation(year);
        return AjaxResult.success(data);
    }

    /**
     * 热门岗位统计
     */
    @GetMapping("/hot/positions")
    public AjaxResult getHotPositions(@RequestParam(defaultValue = "2025") Integer year) {
        List<Map<String, Object>> data = industryDemandService.getHotPositions(year);
        return AjaxResult.success(data);
    }
}