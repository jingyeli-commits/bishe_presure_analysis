-- ====================================
-- 高校毕业生就业压力分析系统数据库
-- ====================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS employment_analysis DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE employment_analysis;

-- ====================================
-- 1. 就业数据表
-- ====================================
DROP TABLE IF EXISTS `employment_data`;
CREATE TABLE `employment_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `university_name` varchar(100) DEFAULT NULL COMMENT '大学名称',
  `major_name` varchar(100) DEFAULT NULL COMMENT '专业名称',
  `graduation_year` int(11) DEFAULT NULL COMMENT '毕业年份',
  `employment_rate` decimal(5,2) DEFAULT NULL COMMENT '就业率',
  `average_salary` decimal(10,2) DEFAULT NULL COMMENT '平均薪资',
  `match_rate` decimal(5,2) DEFAULT NULL COMMENT '对口就业率',
  `further_study_rate` decimal(5,2) DEFAULT NULL COMMENT '继续深造率',
  `graduate_count` int(11) DEFAULT NULL COMMENT '毕业生人数',
  `employed_count` int(11) DEFAULT NULL COMMENT '已就业人数',
  `data_source` varchar(200) DEFAULT NULL COMMENT '数据来源',
  `crawl_time` datetime DEFAULT NULL COMMENT '爬取时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_university` (`university_name`),
  KEY `idx_major` (`major_name`),
  KEY `idx_year` (`graduation_year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='就业数据表';

-- ====================================
-- 2. 行业需求表
-- ====================================
DROP TABLE IF EXISTS `industry_demand`;
CREATE TABLE `industry_demand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `industry_name` varchar(100) DEFAULT NULL COMMENT '行业名称',
  `position_name` varchar(100) DEFAULT NULL COMMENT '岗位名称',
  `demand_count` int(11) DEFAULT NULL COMMENT '需求人数',
  `average_salary` decimal(10,2) DEFAULT NULL COMMENT '平均薪资',
  `salary_range` varchar(50) DEFAULT NULL COMMENT '薪资范围',
  `education_requirement` varchar(50) DEFAULT NULL COMMENT '学历要求',
  `experience_requirement` varchar(50) DEFAULT NULL COMMENT '工作经验要求',
  `location` varchar(50) DEFAULT NULL COMMENT '地区',
  `statistics_year` int(11) DEFAULT NULL COMMENT '统计年份',
  `statistics_month` int(11) DEFAULT NULL COMMENT '统计月份',
  `data_source` varchar(200) DEFAULT NULL COMMENT '数据来源',
  `crawl_time` datetime DEFAULT NULL COMMENT '爬取时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_industry` (`industry_name`),
  KEY `idx_position` (`position_name`),
  KEY `idx_location` (`location`),
  KEY `idx_year_month` (`statistics_year`, `statistics_month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='行业需求表';

-- ====================================
-- 3. 就业压力指标表
-- ====================================
DROP TABLE IF EXISTS `employment_pressure`;
CREATE TABLE `employment_pressure` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `major_name` varchar(100) DEFAULT NULL COMMENT '专业名称',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `pressure_index` decimal(5,2) DEFAULT NULL COMMENT '压力指数(0-100)',
  `competition_ratio` decimal(10,2) DEFAULT NULL COMMENT '竞争比率',
  `supply_demand_ratio` decimal(10,2) DEFAULT NULL COMMENT '供需比',
  `avg_job_search_days` int(11) DEFAULT NULL COMMENT '平均求职时长(天)',
  `salary_satisfaction` decimal(5,2) DEFAULT NULL COMMENT '薪资满意度',
  `position_match_rate` decimal(5,2) DEFAULT NULL COMMENT '岗位匹配度',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_major` (`major_name`),
  KEY `idx_year` (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='就业压力指标表';

-- ====================================
-- 插入示例数据
-- ====================================

-- 插入就业数据示例（2020-2025年数据）
INSERT INTO `employment_data`
(`university_name`, `major_name`, `graduation_year`, `employment_rate`, `average_salary`,
 `match_rate`, `further_study_rate`, `graduate_count`, `employed_count`, `data_source`)
VALUES
-- 2025年数据（最新）
('清华大学', '计算机科学与技术', 2025, 98.80, 18000.00, 93.50, 16.00, 220, 217, '2025届就业质量报告'),
('北京大学', '软件工程', 2025, 98.20, 17500.00, 92.00, 19.00, 200, 196, '2025届就业质量报告'),
('复旦大学', '人工智能', 2025, 97.80, 19000.00, 90.00, 22.00, 180, 176, '2025届就业质量报告'),
('上海交通大学', '数据科学', 2025, 97.50, 16500.00, 91.00, 18.00, 190, 185, '2025届就业质量报告'),
('浙江大学', '金融学', 2025, 95.80, 12500.00, 84.00, 14.00, 250, 240, '2025届就业质量报告'),
('中国科学技术大学', '物联网工程', 2025, 96.50, 15000.00, 88.00, 17.00, 150, 145, '2025届就业质量报告'),
('南京大学', '网络安全', 2025, 97.20, 16000.00, 89.50, 15.00, 140, 136, '2025届就业质量报告'),
('武汉大学', '电子商务', 2025, 94.50, 10000.00, 80.00, 12.00, 280, 265, '2025届就业质量报告'),

-- 2024年数据
('清华大学', '计算机科学与技术', 2024, 98.50, 17000.00, 92.00, 15.00, 210, 207, '2024届就业质量报告'),
('北京大学', '软件工程', 2024, 97.80, 16500.00, 90.00, 18.00, 190, 186, '2024届就业质量报告'),
('复旦大学', '人工智能', 2024, 96.50, 18000.00, 88.00, 20.00, 170, 164, '2024届就业质量报告'),
('上海交通大学', '数据科学', 2024, 97.20, 15500.00, 89.00, 16.00, 180, 175, '2024届就业质量报告'),
('浙江大学', '金融学', 2024, 95.00, 11500.00, 82.00, 12.00, 240, 228, '2024届就业质量报告'),

-- 2023年数据
('清华大学', '计算机科学与技术', 2023, 98.20, 16000.00, 91.00, 14.00, 200, 196, '2023届就业质量报告'),
('北京大学', '软件工程', 2023, 97.50, 15500.00, 89.00, 17.00, 180, 176, '2023届就业质量报告'),
('复旦大学', '人工智能', 2023, 96.00, 17000.00, 87.00, 19.00, 160, 154, '2023届就业质量报告'),
('上海交通大学', '数据科学', 2023, 96.80, 14500.00, 88.00, 15.00, 170, 165, '2023届就业质量报告'),
('浙江大学', '金融学', 2023, 94.50, 10500.00, 80.00, 11.00, 230, 217, '2023届就业质量报告');

-- 插入行业需求示例数据（2025年最新）
INSERT INTO `industry_demand`
(`industry_name`, `position_name`, `demand_count`, `average_salary`, `salary_range`,
 `education_requirement`, `experience_requirement`, `location`, `statistics_year`, `statistics_month`, `data_source`)
VALUES
-- 互联网行业
('互联网', 'AI工程师', 800, 18000.00, '12000-25000', '本科', '1-3年', '北京', 2025, 6, '2025年Q2招聘报告'),
('互联网', '软件开发工程师', 1200, 15000.00, '10000-22000', '本科', '1-3年', '北京', 2025, 6, '2025年Q2招聘报告'),
('互联网', '大数据工程师', 600, 16000.00, '11000-23000', '本科', '2-5年', '上海', 2025, 6, '2025年Q2招聘报告'),
('互联网', '产品经理', 400, 17000.00, '12000-25000', '本科', '3-5年', '深圳', 2025, 6, '2025年Q2招聘报告'),
('互联网', '前端工程师', 900, 13000.00, '9000-20000', '本科', '1-3年', '杭州', 2025, 6, '2025年Q2招聘报告'),

-- 金融行业
('金融', '金融科技工程师', 500, 14000.00, '10000-20000', '本科', '2-5年', '上海', 2025, 6, '2025年Q2招聘报告'),
('金融', '数据分析师', 450, 12000.00, '8000-18000', '本科', '1-3年', '北京', 2025, 6, '2025年Q2招聘报告'),
('金融', '风险管理专员', 350, 11000.00, '8000-16000', '本科', '2-5年', '深圳', 2025, 6, '2025年Q2招聘报告'),

-- 制造业
('制造业', '智能制造工程师', 700, 11000.00, '8000-16000', '本科', '1-3年', '苏州', 2025, 6, '2025年Q2招聘报告'),
('制造业', '自动化工程师', 600, 10500.00, '7500-15000', '本科', '1-3年', '武汉', 2025, 6, '2025年Q2招聘报告'),

-- 新能源行业（2025年热门）
('新能源', '新能源汽车工程师', 550, 13000.00, '9000-18000', '本科', '1-3年', '合肥', 2025, 6, '2025年Q2招聘报告'),
('新能源', '电池技术工程师', 400, 14000.00, '10000-20000', '硕士', '2-5年', '南京', 2025, 6, '2025年Q2招聘报告'),

-- 教育行业
('教育', '在线教育讲师', 650, 9500.00, '6000-14000', '本科', '不限', '广州', 2025, 6, '2025年Q2招聘报告'),
('教育', '教育产品经理', 300, 12000.00, '8000-18000', '本科', '2-5年', '北京', 2025, 6, '2025年Q2招聘报告');

-- 插入就业压力数据（2020-2025年趋势）
INSERT INTO `employment_pressure`
(`major_name`, `year`, `pressure_index`, `competition_ratio`, `supply_demand_ratio`,
 `avg_job_search_days`, `salary_satisfaction`, `position_match_rate`)
VALUES
-- 2025年数据
('计算机科学与技术', 2025, 42.00, 2.10, 0.78, 38, 82.00, 90.00),
('软件工程', 2025, 39.50, 1.95, 0.75, 35, 84.00, 92.00),
('人工智能', 2025, 35.00, 1.75, 0.70, 30, 86.00, 93.00),
('数据科学', 2025, 40.00, 2.00, 0.76, 36, 83.00, 91.00),
('金融学', 2025, 58.00, 3.20, 1.15, 58, 72.00, 78.00),
('市场营销', 2025, 55.00, 3.00, 1.10, 55, 74.00, 80.00),
('电子商务', 2025, 52.00, 2.80, 1.05, 50, 76.00, 82.00),

-- 2024年数据
('计算机科学与技术', 2024, 45.50, 2.30, 0.85, 45, 78.50, 88.00),
('软件工程', 2024, 42.00, 2.10, 0.80, 40, 80.00, 90.00),
('人工智能', 2024, 38.50, 1.90, 0.75, 35, 82.00, 91.00),
('金融学', 2024, 62.00, 3.50, 1.20, 65, 68.00, 75.00),
('市场营销', 2024, 58.00, 3.20, 1.15, 60, 70.00, 78.00),

-- 2023年数据
('计算机科学与技术', 2023, 48.00, 2.50, 0.90, 50, 75.00, 85.00),
('软件工程', 2023, 45.00, 2.30, 0.88, 48, 77.00, 87.00),
('人工智能', 2023, 42.00, 2.15, 0.82, 42, 79.00, 89.00),
('金融学', 2023, 65.00, 3.80, 1.25, 70, 65.00, 72.00),
('市场营销', 2023, 62.00, 3.50, 1.20, 68, 67.00, 75.00);
