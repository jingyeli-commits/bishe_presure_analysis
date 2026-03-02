package com.ruoyi.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 就业数据实体
 * 
 * @author ruoyi
 */
@Data
@TableName("employment_data")
public class EmploymentData implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 大学名称 */
    private String universityName;

    /** 专业名称 */
    private String majorName;

    /** 毕业年份 */
    private Integer graduationYear;

    /** 就业率 */
    private BigDecimal employmentRate;

    /** 平均薪资 */
    private BigDecimal averageSalary;

    /** 对口就业率 */
    private BigDecimal matchRate;

    /** 继续深造率 */
    private BigDecimal furtherStudyRate;

    /** 毕业生人数 */
    private Integer graduateCount;

    /** 已就业人数 */
    private Integer employedCount;

    /** 数据来源 */
    private String dataSource;

    /** 爬取时间 */
    private Date crawlTime;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;
}
