(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('mainController',
        ['$scope', 'menuManager','taskService',
            function ($scope, menuManager,taskService) {
                $scope.menuServer = menuManager;
                $scope.status = "扫描上方二维码";
                console.info(JSON.stringify($scope.menuServer));
                var qrcode = new QRCode(
                    document.getElementById("QEM"), {
                    width : 315,//设置宽高
                    height : 315
                });
                var websocket = null;
                websocket = new SockJS("http://"+document.location.host+"/todo/api/websocket");
                websocket.onopen = onOpen;
                websocket.onmessage = onMessage;
                websocket.onerror = onError;
                websocket.onclose = onClose;

                function onOpen(openEvt) {
                    console.info(JSON.stringify(openEvt));
                }

                function onMessage(evt) {
                    var obj=JSON.parse(evt.data)
                    console.info(JSON.stringify(evt.data));
                    if(obj.type=='onLogin'){
                        //$('#QEM').empty();
                        qrcode.clear();

                        qrcode.makeCode("http://"+document.location.host+"/todo/api/todo/login?tocken="+obj.tocken)
                         // new QRCode(document.getElementById("QEM"),
                         //     "http://"+document.location.host+"/todo/api/todo/login?tocken="+obj.tocken);
                         $('#QEM_Modal').modal({
                             closable:false
                         }).modal('show')
                        $scope.status="扫描上方二维码";
                        $scope.$apply();
                    }else if(obj.type=="scan"){
                        $scope.status="扫码成功，请在手机上确认";
                        $scope.$apply();
                    }else if(obj.type=="ok"){
                        $scope.userName=obj.account;
                        $scope.status="正在登录";
                        $scope.$apply();
                        setTimeout(function(){
                            $('#QEM_Modal').modal({
                                closable:false
                            }).modal('hide')
                        }, 3000);

                    }

                }
                function onError(openEvt) {
                    console.info(JSON.stringify(openEvt));
                }
                function onClose(openEvt) {
                    console.info(JSON.stringify(openEvt));
                }

                $scope.doSend=function() {
                    websocket.send($scope.message);//调用后台handleTextMessage方法
                };
                $scope.login=function() {
                    var a={data:"login"}
                    websocket.send(a);//调用后台handleTextMessage方法
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
