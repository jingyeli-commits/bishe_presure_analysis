package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 就业压力指标实体
 * 
 * @author ruoyi
 */
@Data
@TableName("employment_pressure")
public class EmploymentPressure implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 专业名称 */
    private String majorName;

    /** 年份 */
    private Integer year;

    /** 压力指数(0-100) */
    private BigDecimal pressureIndex;

    /** 竞争比率 */
    private BigDecimal competitionRatio;

    /** 供需比 */
    private BigDecimal supplyDemandRatio;

    /** 平均求职时长(天) */
    private Integer avgJobSearchDays;

    /** 薪资满意度 */
    private BigDecimal salarySatisfaction;

    /** 岗位匹配度 */
    private BigDecimal positionMatchRate;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;
}
