package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.EmploymentPressure;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 就业压力Mapper
 * 
 * @author ruoyi
 */
@Mapper
public interface EmploymentPressureMapper extends BaseMapper<EmploymentPressure> {

    /**
     * 查询压力指数最高的专业
     */
    @Select("SELECT major_name as major, pressure_index as pressure " +
            "FROM employment_pressure " +
            "WHERE year = #{year} " +
            "ORDER BY pressure_index DESC " +
            "LIMIT 10")
    List<Map<String, Object>> selectTopPressureMajors(Integer year);

    /**
     * 按年份统计平均压力指数
     */
    @Select("SELECT year, AVG(pressure_index) as avgPressure " +
            "FROM employment_pressure " +
            "GROUP BY year " +
            "ORDER BY year")
    List<Map<String, Object>> selectAvgPressureByYear();

    /**
     * 查询专业压力趋势
     */
    @Select("SELECT year, pressure_index as pressure " +
            "FROM employment_pressure " +
            "WHERE major_name = #{majorName} " +
            "ORDER BY year")
    List<Map<String, Object>> selectPressureTrendByMajor(String majorName);
}
