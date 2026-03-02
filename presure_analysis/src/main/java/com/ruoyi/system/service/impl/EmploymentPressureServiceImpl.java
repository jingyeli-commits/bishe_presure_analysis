package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.EmploymentPressure;
import com.ruoyi.system.mapper.EmploymentPressureMapper;
import com.ruoyi.system.service.IEmploymentPressureService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 就业压力Service实现
 * 
 * @author ruoyi
 */
@Service
public class EmploymentPressureServiceImpl extends ServiceImpl<EmploymentPressureMapper, EmploymentPressure> 
        implements IEmploymentPressureService {

    @Override
    public List<Map<String, Object>> getTopPressureMajors(Integer year) {
        return baseMapper.selectTopPressureMajors(year);
    }

    @Override
    public List<Map<String, Object>> getAvgPressureByYear() {
        return baseMapper.selectAvgPressureByYear();
    }

    @Override
    public List<Map<String, Object>> getPressureTrendByMajor(String majorName) {
        return baseMapper.selectPressureTrendByMajor(majorName);
    }
}
