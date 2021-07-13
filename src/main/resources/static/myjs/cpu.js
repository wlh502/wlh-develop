var LOGIN_USER = null;

/**
 * 订阅CPU使用率
 */
function subscribeCpuUseRate(){
    if(stompClient !== null){
        cpuUseRateNotice();
        console.log("cpu使用率订阅");
        // 后续实现登录了，使用/user通道，现在先用session代替
        stompClient.subscribe("/cpuinfo/cpuUseRate/"+LOGIN_USER,function(message){
            console.log("接受到消息:"+message);
        });
        console.log("cpu使用率订阅成功");
    }
}


function cpuUseRateNotice(){
    stompClient.send("/cpu_watch/cpuUseRate");
}