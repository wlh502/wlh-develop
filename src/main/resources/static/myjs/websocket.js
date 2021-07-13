var stompClient = null;
const HOST = document.domain;
// 设置 WebSocket 进入端点
const SOCKET_ENDPOINT = "/systeminfo";



function connect(subscribe){
    if(stompClient == null){
        const socket = new SockJS(SOCKET_ENDPOINT);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log("websocket连接成功")
            console.log("开始执行订阅操作")
            subscribe.forEach(function (item){
                item();
            });
        });
    }
}

function disconnect(){
    if (stompClient !== null) {
        stompClient.disconnect();
        console.log("websocket断开连接");
    }
}

