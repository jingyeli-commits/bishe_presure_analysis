package com.ruoyi.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.system.domain.EmploymentData;

import java.util.List;
import java.util.Map;

/**
 * 就业数据Service接口
 * 
 * @author ruoyi
 */
public interface IEmploymentDataService extends IService<EmploymentData> {

    /**
     * 查询就业数据列表
     */
    List<EmploymentData> selectEmploymentDataList(EmploymentData employmentData);

    /**
     * 按年份统计就业率
     */
    List<Map<String, Object>> getEmploymentRateByYear();

    /**
     * 按专业统计平均薪资
     */
    List<Map<String, Object>> getTopSalaryByMajor();

    /**
     * 按大学统计就业率
     */
    List<Map<String, Object>> getEmploymentRateByUniversity(Integer year);

    /**
     * 统计各专业毕业生人数
     */
    List<Map<String, Object>> getGraduateCountByMajor(Integer year);

    /**
     * 保存爬取的数据
     */
    boolean saveEmploymentData(List<EmploymentData> dataList);
}
