(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('mainController',
        ['$scope', 'menuManager','taskService',
            function ($scope, menuManager,taskService) {
                $scope.menuServer = menuManager;
                console.info(JSON.stringify($scope.menuServer));
                /*alert(window.location.href);
                alert(document.location);
                alert(document.location.host);*/
                var websocket = null;
                //websocket = new WebSocket("ws://192.168.1.108:8080/api/websocket");
                websocket = new SockJS("http://"+document.location.host+"/api/websocket");
                websocket.onopen = onOpen;
                websocket.onmessage = onMessage;
                websocket.onerror = onError;
                websocket.onclose = onClose;

                function onOpen(openEvt) {
                    //alert(openEvt.Data);
                }

                function onMessage(evt) {
                    alert(evt.data);
                    new QRCode(document.getElementById("QEM"),
                        "http://192.168.1.234:8080/api/todo/login?tocken="+evt.data);

                }
                function onError() {}
                function onClose() {}

                $scope.doSend=function() {
                    websocket.send($scope.message);//调用后台handleTextMessage方法
                };
                window.close=function()
                {
                    websocket.onclose();
                }
                $scope.gotoTransh=function(){
                    $('.shape').shape('flip right');
                };
$scope.addTodoModal={};
                $scope.rightShow = function () {
                    $('.ui.sucessMsg.sidebar')
                        .sidebar('setting', 'transition', 'overlay')
                        .sidebar('toggle');
                }
            }]);
})(angular);
