let LOGIN_USER = null;

/**
 * 订阅CPU使用率
 */
function subscribeCpuUseRate(){
    if(stompClient !== null){
        let cpuUseRateChart = echarts.init(document.getElementById('cpuUseRate'));
        initCpuUseRateChart(cpuUseRateChart);
        cpuUseRateNotice();
        // 后续实现登录了，使用/user通道，现在先用session代替
       // cpuUseRateChart.showLoading();
        stompClient.subscribe("/cpuinfo/cpuUseRate/"+LOGIN_USER,function(result){
            cpuUseRateChart.setOption({
                series: [{
                    name:'cpuUseRate',
                    data: [{
                        value: result.body,
                        name: 'CPU使用率'
                    }]
                }]
            });

        });
    }
}


function cpuUseRateNotice(){
    stompClient.send("/cpuWatch/cpuUseRate",{"userId":LOGIN_USER,"jobName":"cpuUseRateJob"});
}

function initCpuUseRateChart(chart){
    chart.setOption({
        tooltip: {
            formatter: '{c}%'
        },
        series: [{
            name:'cpuUseRate',
            type: 'gauge',
            progress: {
                show: true
            },
            detail: {
                valueAnimation: true,
                formatter: '{value}'
            },
            data: [{
                value: 0,
                name: 'CPU使用率'
            }]
        }]
    });

}