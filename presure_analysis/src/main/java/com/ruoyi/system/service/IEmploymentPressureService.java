package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.EmploymentPressure;

import java.util.List;
import java.util.Map;

/**
 * 就业压力Service接口
 * 
 * @author ruoyi
 */
public interface IEmploymentPressureService extends IService<EmploymentPressure> {

    /**
     * 查询压力指数最高的专业
     */
    List<Map<String, Object>> getTopPressureMajors(Integer year);

    /**
     * 按年份统计平均压力指数
     */
    List<Map<String, Object>> getAvgPressureByYear();

    /**
     * 查询专业压力趋势
     */
    List<Map<String, Object>> getPressureTrendByMajor(String majorName);
}
