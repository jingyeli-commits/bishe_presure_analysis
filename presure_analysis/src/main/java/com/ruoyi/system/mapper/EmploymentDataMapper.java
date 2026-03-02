package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.domain.EmploymentData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 就业数据Mapper
 * 
 * @author ruoyi
 */
@Mapper
public interface EmploymentDataMapper extends BaseMapper<EmploymentData> {

    /**
     * 按年份统计就业率
     */
    @Select("SELECT graduation_year as year, AVG(employment_rate) as rate " +
            "FROM employment_data " +
            "GROUP BY graduation_year " +
            "ORDER BY graduation_year")
    List<Map<String, Object>> selectEmploymentRateByYear();

    /**
     * 按专业统计平均薪资
     */
    @Select("SELECT major_name as major, AVG(average_salary) as salary " +
            "FROM employment_data " +
            "GROUP BY major_name " +
            "ORDER BY salary DESC " +
            "LIMIT 10")
    List<Map<String, Object>> selectTopSalaryByMajor();

    /**
     * 按大学统计就业率
     */
    @Select("SELECT university_name as university, AVG(employment_rate) as rate " +
            "FROM employment_data " +
            "WHERE graduation_year = #{year} " +
            "GROUP BY university_name " +
            "ORDER BY rate DESC " +
            "LIMIT 15")
    List<Map<String, Object>> selectEmploymentRateByUniversity(Integer year);

    /**
     * 统计各专业毕业生人数
     */
    @Select("SELECT major_name as major, SUM(graduate_count) as count " +
            "FROM employment_data " +
            "WHERE graduation_year = #{year} " +
            "GROUP BY major_name " +
            "ORDER BY count DESC " +
            "LIMIT 10")
    List<Map<String, Object>> selectGraduateCountByMajor(Integer year);
}
