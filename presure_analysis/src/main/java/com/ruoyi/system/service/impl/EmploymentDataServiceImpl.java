package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.EmploymentData;
import com.ruoyi.system.mapper.EmploymentDataMapper;
import com.ruoyi.system.service.IEmploymentDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 就业数据Service实现
 * 
 * @author ruoyi
 */
@Service
public class EmploymentDataServiceImpl extends ServiceImpl<EmploymentDataMapper, EmploymentData> 
        implements IEmploymentDataService {

    @Override
    public List<EmploymentData> selectEmploymentDataList(EmploymentData employmentData) {
        QueryWrapper<EmploymentData> queryWrapper = new QueryWrapper<>();
        
        if (employmentData.getUniversityName() != null && !employmentData.getUniversityName().isEmpty()) {
            queryWrapper.like("university_name", employmentData.getUniversityName());
        }
        if (employmentData.getMajorName() != null && !employmentData.getMajorName().isEmpty()) {
            queryWrapper.like("major_name", employmentData.getMajorName());
        }
        if (employmentData.getGraduationYear() != null) {
            queryWrapper.eq("graduation_year", employmentData.getGraduationYear());
        }
        
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getEmploymentRateByYear() {
        return baseMapper.selectEmploymentRateByYear();
    }

    @Override
    public List<Map<String, Object>> getTopSalaryByMajor() {
        return baseMapper.selectTopSalaryByMajor();
    }

    @Override
    public List<Map<String, Object>> getEmploymentRateByUniversity(Integer year) {
        return baseMapper.selectEmploymentRateByUniversity(year);
    }

    @Override
    public List<Map<String, Object>> getGraduateCountByMajor(Integer year) {
        return baseMapper.selectGraduateCountByMajor(year);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveEmploymentData(List<EmploymentData> dataList) {
        return this.saveBatch(dataList);
    }
}
