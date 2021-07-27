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
        stompClient.subscribe("/cpuinfo/cpuUseRate/"+LOGIN_USER,function(message){
            //console.log(cpuUseRateChart)

        });
    }
}


function cpuUseRateNotice(){
    stompClient.send("/cpuWatch/cpuUseRate",{"userId":LOGIN_USER,"jobName":"cpuUseRateJob"});
}

function initCpuUseRateChart(chart){

}