package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.system.domain.IndustryDemand;
import com.ruoyi.system.mapper.IndustryDemandMapper;
import com.ruoyi.system.service.IIndustryDemandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 行业需求Service实现
 * 
 * @author ruoyi
 */
@Service
public class IndustryDemandServiceImpl extends ServiceImpl<IndustryDemandMapper, IndustryDemand> 
        implements IIndustryDemandService {

    @Override
    public List<Map<String, Object>> getDemandByIndustry(Integer year) {
        return baseMapper.selectDemandByIndustry(year);
    }

    @Override
    public List<Map<String, Object>> getDemandByLocation(Integer year) {
        return baseMapper.selectDemandByLocation(year);
    }

    @Override
    public List<Map<String, Object>> getHotPositions(Integer year) {
        return baseMapper.selectHotPositions(year);
    }
}
