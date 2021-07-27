let stompClient = null;
const HOST = document.domain;
// 设置 WebSocket 进入端点
const SOCKET_ENDPOINT = "/systeminfo";



function connect(subscribe){
    if(stompClient == null){
        let socket = new SockJS(SOCKET_ENDPOINT);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            subscribe.forEach(function (item){
                item();
            });
        });
    }
}

function disconnect(){
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}


