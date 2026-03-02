// 页面加载完成后执行
document.addEventListener('DOMContentLoaded', function() {
    loadStatistics();
    loadCharts();
});

// 加载统计数据
function loadStatistics() {
    axios.get('/system/employment/statistics')
        .then(response => {
            if (response.data.code === 200) {
                const data = response.data.data;
                document.getElementById('totalCount').textContent = data.totalCount || 0;
                document.getElementById('avgRate').textContent = (data.avgEmploymentRate || 0) + '%';
                document.getElementById('avgSalary').textContent = data.avgSalary || 0;
                document.getElementById('totalGraduates').textContent = data.totalGraduates || 0;
            }
        })
        .catch(error => {
            console.error('加载统计数据失败:', error);
        });
}

// 加载所有图表
function loadCharts() {
    loadEmploymentRateChart();
    loadSalaryChart();
    loadUniversityChart();
    loadGraduateCountChart();
}

// 历年就业率趋势图 - 红色风格
function loadEmploymentRateChart() {
    axios.get('/system/employment/rate/year')
        .then(response => {
            if (response.data.code === 200) {
                const data = response.data.data;
                const years = data.map(item => item.year);
                const rates = data.map(item => parseFloat(item.rate).toFixed(2));

                const chart = echarts.init(document.getElementById('employmentRateChart'));
                const option = {
                    tooltip: {
                        trigger: 'axis',
                        formatter: '{b}年: {c}%',
                        backgroundColor: '#fff',
                        borderColor: '#ff0000',
                        borderWidth: 1,
                        textStyle: { color: '#333' }
                    },
                    xAxis: {
                        type: 'category',
                        data: years,
                        axisLine: {
                            lineStyle: { color: '#333', width: 1 }
                        },
                        axisLabel: {
                            color: '#333',
                            rotate: 0
                        },
                        axisTick: {
                            lineStyle: { color: '#333' }
                        }
                    },
                    yAxis: {
                        type: 'value',
                        name: '就业率(%)',
                        nameTextStyle: { color: '#333' },
                        min: 80,
                        max: 100,
                        axisLine: {
                            lineStyle: { color: '#333', width: 1 }
                        },
                        axisLabel: {
                            color: '#333'
                        },
                        axisTick: {
                            lineStyle: { color: '#333' }
                        },
                        splitLine: {
                            lineStyle: {
                                color: '#eee',
                                width: 1,
                                type: 'solid'
                            }
                        }
                    },
                    series: [{
                        data: rates,
                        type: 'line',
                        lineStyle: {
                            width: 3,
                            color: '#ff0000'
                        },
                        itemStyle: {
                            color: '#ff0000',
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        symbol: 'circle',
                        symbolSize: 8
                    }],
                    grid: {
                        left: '10%',
                        right: '5%',
                        bottom: '15%'
                    }
                };
                chart.setOption(option);

                // 响应式
                window.addEventListener('resize', () => chart.resize());
            }
        })
        .catch(error => {
            console.error('加载就业率趋势图失败:', error);
        });
}

// 各专业平均薪资柱状图 - 红色风格
function loadSalaryChart() {
    axios.get('/system/employment/salary/major')
        .then(response => {
            if (response.data.code === 200) {
                const data = response.data.data;
                const majors = data.map(item => item.major);
                const salaries = data.map(item => parseFloat(item.salary).toFixed(0));

                const chart = echarts.init(document.getElementById('salaryChart'));
                const option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        formatter: '{b}: ¥{c}',
                        backgroundColor: '#fff',
                        borderColor: '#ff0000',
                        borderWidth: 1,
                        textStyle: { color: '#333' }
                    },
                    xAxis: {
                        type: 'value',
                        name: '平均薪资(元)',
                        nameTextStyle: { color: '#333' },
                        axisLine: {
                            lineStyle: { color: '#333', width: 1 }
                        },
                        axisLabel: {
                            color: '#333'
                        },
                        axisTick: {
                            lineStyle: { color: '#333' }
                        },
                        splitLine: {
                            lineStyle: {
                                color: '#eee',
                                width: 1,
                                type: 'solid'
                            }
                        }
                    },
                    yAxis: {
                        type: 'category',
                        data: majors,
                        axisLine: {
                            lineStyle: { color: '#333', width: 1 }
                        },
                        axisLabel: {
                            interval: 0,
                            fontSize: 12,
                            color: '#333'
                        },
                        axisTick: {
                            lineStyle: { color: '#333' }
                        }
                    },
                    series: [{
                        data: salaries,
                        type: 'bar',
                        barWidth: '60%',
                        itemStyle: {
                            color: '#ff0000'
                        },
                        label: {
                            show: true,
                            position: 'right',
                            formatter: '¥{c}',
                            color: '#ff0000',
                            fontWeight: 'bold'
                        }
                    }],
                    grid: {
                        left: '25%',
                        right: '5%',
                        bottom: '10%'
                    }
                };
                chart.setOption(option);

                window.addEventListener('resize', () => chart.resize());
            }
        })
        .catch(error => {
            console.error('加载薪资图表失败:', error);
        });
}

// 各大学就业率对比图 - 红色风格
function loadUniversityChart() {
    axios.get('/system/employment/rate/university?year=2025')
        .then(response => {
            if (response.data.code === 200) {
                const data = response.data.data;
                const universities = data.map(item => item.university);
                const rates = data.map(item => parseFloat(item.rate).toFixed(2));

                const chart = echarts.init(document.getElementById('universityChart'));
                const option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        formatter: '{b}: {c}%',
                        backgroundColor: '#fff',
                        borderColor: '#ff0000',
                        borderWidth: 1,
                        textStyle: { color: '#333' }
                    },
                    xAxis: {
                        type: 'category',
                        data: universities,
                        axisLine: {
                            lineStyle: { color: '#333', width: 1 }
                        },
                        axisLabel: {
                            rotate: 30,
                            interval: 0,
                            fontSize: 12,
                            color: '#333'
                        },
                        axisTick: {
                            lineStyle: { color: '#333' }
                        }
                    },
                    yAxis: {
                        type: 'value',
                        name: '就业率(%)',
                        nameTextStyle: { color: '#333' },
                        min: 85,
                        max: 100,
                        axisLine: {
                            lineStyle: { color: '#333', width: 1 }
                        },
                        axisLabel: {
                            color: '#333'
                        },
                        axisTick: {
                            lineStyle: { color: '#333' }
                        },
                        splitLine: {
                            lineStyle: {
                                color: '#eee',
                                width: 1,
                                type: 'solid'
                            }
                        }
                    },
                    series: [{
                        data: rates,
                        type: 'bar',
                        barWidth: '60%',
                        itemStyle: {
                            color: '#ff0000'
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}%',
                            color: '#ff0000',
                            fontWeight: 'bold'
                        }
                    }],
                    grid: {
                        left: '8%',
                        right: '5%',
                        bottom: '20%'
                    }
                };
                chart.setOption(option);

                window.addEventListener('resize', () => chart.resize());
            }
        })
        .catch(error => {
            console.error('加载大学就业率图表失败:', error);
        });
}

// 各专业毕业生人数饼图 - 红色系风格
function loadGraduateCountChart() {
    axios.get('/system/employment/count/major?year=2025')
        .then(response => {
            if (response.data.code === 200) {
                const data = response.data.data.map(item => ({
                    name: item.major,
                    value: item.count
                }));

                const chart = echarts.init(document.getElementById('graduateCountChart'));
                const option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{b}: {c}人 ({d}%)',
                        backgroundColor: '#fff',
                        borderColor: '#ff0000',
                        borderWidth: 1,
                        textStyle: { color: '#333' }
                    },
                    legend: {
                        bottom: '5%',
                        left: 'center',
                        textStyle: {
                            color: '#333'
                        }
                    },
                    series: [{
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderColor: '#fff',
                            borderWidth: 2
                        },
                        label: {
                            show: true,
                            formatter: '{b}: {d}%',
                            color: '#333'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 14,
                                fontWeight: 'bold'
                            }
                        },
                        data: data.map((item, index) => ({
                            ...item,
                            itemStyle: {
                                // 红色系渐变，从浅红到深红
                                color: index === 0 ? '#ff6666' :
                                       index === 1 ? '#ff4444' :
                                       index === 2 ? '#ff2222' :
                                       index === 3 ? '#ff0000' :
                                       index === 4 ? '#cc0000' :
                                       index === 5 ? '#aa0000' :
                                       index === 6 ? '#880000' : '#660000'
                            }
                        }))
                    }]
                };
                chart.setOption(option);

                window.addEventListener('resize', () => chart.resize());
            }
        })
        .catch(error => {
            console.error('加载毕业生人数图表失败:', error);
        });
}

// 启动爬虫
function startCrawler(type) {
    const btn = event.target.closest('button');
    const originalText = btn.innerHTML;
    btn.disabled = true;
    btn.innerHTML = '<span class="loading"></span> 爬取中...';

    const url = type === 'employment' ? '/crawler/employment/start' : '/crawler/industry/start';

    axios.post(url)
        .then(response => {
            showMessage(response.data.msg, 'success');
            setTimeout(() => {
                refreshData();
            }, 2000);
        })
        .catch(error => {
            showMessage('操作失败: ' + (error.response?.data?.msg || error.message), 'error');
        })
        .finally(() => {
            btn.disabled = false;
            btn.innerHTML = originalText;
        });
}

// 刷新数据
function refreshData() {
    loadStatistics();
    loadCharts();
    showMessage('数据已刷新', 'success');
}

// 显示消息
function showMessage(text, type = 'success') {
    const message = document.createElement('div');
    message.className = `message ${type}`;
    message.textContent = text;
    document.body.appendChild(message);

    setTimeout(() => {
        message.remove();
    }, 3000);
}