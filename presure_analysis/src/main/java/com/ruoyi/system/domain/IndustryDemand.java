package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 行业需求数据实体
 * 
 * @author ruoyi
 */
@Data
@TableName("industry_demand")
public class IndustryDemand implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 行业名称 */
    private String industryName;

    /** 岗位名称 */
    private String positionName;

    /** 需求人数 */
    private Integer demandCount;

    /** 平均薪资 */
    private BigDecimal averageSalary;

    /** 薪资范围 */
    private String salaryRange;

    /** 学历要求 */
    private String educationRequirement;

    /** 工作经验要求 */
    private String experienceRequirement;

    /** 地区 */
    private String location;

    /** 统计年份 */
    private Integer statisticsYear;

    /** 统计月份 */
    private Integer statisticsMonth;

    /** 数据来源 */
    private String dataSource;

    /** 爬取时间 */
    private Date crawlTime;

    /** 创建时间 */
    private Date createTime;

    /** 备注 */
    private String remark;
}
