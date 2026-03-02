package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.IndustryDemand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 行业需求Mapper
 * 
 * @author ruoyi
 */
@Mapper
public interface IndustryDemandMapper extends BaseMapper<IndustryDemand> {

    /**
     * 按行业统计需求人数
     */
    @Select("SELECT industry_name as industry, SUM(demand_count) as count " +
            "FROM industry_demand " +
            "WHERE statistics_year = #{year} " +
            "GROUP BY industry_name " +
            "ORDER BY count DESC " +
            "LIMIT 10")
    List<Map<String, Object>> selectDemandByIndustry(Integer year);

    /**
     * 按地区统计需求人数
     */
    @Select("SELECT location, SUM(demand_count) as count " +
            "FROM industry_demand " +
            "WHERE statistics_year = #{year} " +
            "GROUP BY location " +
            "ORDER BY count DESC " +
            "LIMIT 10")
    List<Map<String, Object>> selectDemandByLocation(Integer year);

    /**
     * 热门岗位统计
     */
    @Select("SELECT position_name as position, COUNT(*) as count, AVG(average_salary) as salary " +
            "FROM industry_demand " +
            "WHERE statistics_year = #{year} " +
            "GROUP BY position_name " +
            "ORDER BY count DESC " +
            "LIMIT 15")
    List<Map<String, Object>> selectHotPositions(Integer year);
}
