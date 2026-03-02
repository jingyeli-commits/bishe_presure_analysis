package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.IndustryDemand;

import java.util.List;
import java.util.Map;

/**
 * 行业需求Service接口
 * 
 * @author ruoyi
 */
public interface IIndustryDemandService extends IService<IndustryDemand> {

    /**
     * 按行业统计需求人数
     */
    List<Map<String, Object>> getDemandByIndustry(Integer year);

    /**
     * 按地区统计需求人数
     */
    List<Map<String, Object>> getDemandByLocation(Integer year);

    /**
     * 热门岗位统计
     */
    List<Map<String, Object>> getHotPositions(Integer year);
}
