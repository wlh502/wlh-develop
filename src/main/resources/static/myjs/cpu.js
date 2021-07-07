var LOGIN_USER = null;

/**
 * 订阅CPU使用率
 */
function subscribeCpuUseRate(){
    if(stompClient !== null){
        cpuUseRateNotice();
        stompClient.subscribe("/user/"+LOGIN_USER+"/cpuinfo/cpuUseRate",function(message){
            console.log(message);
        });
        console.log("cpu使用率订阅成功");
    }
}


function cpuUseRateNotice(){
    console.log("cpu使用率订阅");
    stompClient.send("/cpu_watch/cpuUseRate");
}