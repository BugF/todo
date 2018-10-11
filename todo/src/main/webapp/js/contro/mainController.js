(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('mainController',
        ['$scope', 'menuManager','taskService',
            'listService','$location','userService',
            function ($scope, menuManager,taskService,
                      listService,$location,userService) {
                $scope.menuServer = menuManager;
                $scope.status = "扫描上方二维码";
                $scope.loading=false;
                var titles=[];
                // function addTitle(title) {
                //     titles.push()
                // }
                function getTitle(){
                    var i=0;
                    var titlePro="新建清单";
                    var t=titlePro;
                    while($.inArray(t,$scope.menuServer.listMenuTitleArr)>=0){
                        i++;
                        t=titlePro+i;
                    }
                    return t;
                }
                console.info(JSON.stringify($scope.menuServer));
                var qrcode = new QRCode(
                    document.getElementById("QEM"), {
                    width : 315,//设置宽高
                    height : 315
                });//init();
                ini()
                $scope.uuid="";
                function ini(){
                    userService.isLogin().then(
                        function (value) {
                            if(value.status=='true'){
                                websocket = new SockJS("http://"+document.location.host+"/todo/api/websocket?account="+value.account);
                            }else{
                                $scope.uuid=uuid();
                                websocket = new SockJS("http://"+document.location.host+"/todo/api/websocket?account="+$scope.uuid);
                                qrcode.clear();
                                qrcode.makeCode(
                                    "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                                    "appid=wx9fb5d2c14742fa1f&" +
                                    "redirect_uri=http%3a%2f%2f"+document.location.host+"%2ftodo%2fapi%2fuser%2flogin" +
                                    "&response_type=code&scope=snsapi_base&state="+$scope.uuid
                                    +"#wechat_redirec");
                                //qrcode.makeCode("http://"+document.location.host+"/todo/api/user/login?tocken="+$scope.uuid);
                                $('.ui.login.modal').modal({ closable:false})
                                    .modal('show')
                                ;
                            }
                            websocket.onopen = onOpen;
                            websocket.onmessage = onMessage;
                            websocket.onerror = onError;
                            websocket.onclose = onClose;
                        }
                    )
                }

                var websocket = null;
                function init() {
                    taskService.loginUser().then(
                        function (obj) {
                            if(null==obj.account){
                                websocket = new SockJS("http://"+document.location.host+"/todo/api/websocket");

                            }else{

                                websocket = new SockJS("http://"+document.location.host+"/todo/api/websocket?account="+obj.account);
                                listAllLIST();
                            }
                            websocket.onopen = onOpen;
                            websocket.onmessage = onMessage;
                            websocket.onerror = onError;
                            websocket.onclose = onClose;
                        }
                    )
                }

              //  websocket = new SockJS("http://"+document.location.host+"/todo/api/websocket?ppp=123");

                $scope.addList=function(){
                    $scope.loading=true;
                    var title=getTitle();
                    listService.create(title).then(
                        function (data) {
                            if(data.status=='true'){
                                $scope.menuServer.listMenuTitleArr.push(data.datas.title);
                                $scope.menuServer.listMenuMap[data.datas.id]=data.datas;
                                $scope.menuServer.listMenuIdArr.push(data.datas.id);
                                goToList(data.datas.id);
                            }
                            $scope.loading=false;
                        }
                    )
                }
                function goToList(id){
                    $location.path('/list/' + id);
                }
                $scope.goToList=function(id){
                    goToList(id);
                }

                $scope._LISTS=[];
                function listAllLIST() {
                    $scope._LISTS=[];
                    listService.listAll().then(
                        function (data) {
                            if(data.status=='true'){
                                $scope._LISTS=data.datas;
                                $.each(data.datas,function (i,obj) {
                                    titles.push(obj.title)
                                })
                                console.info(JSON.stringify(data.datas));
                            }
                        }
                    )
                }

                function onOpen(openEvt) {
                    console.info(JSON.stringify(openEvt));
                }

                function onMessage(evt) {
                    var obj=JSON.parse(evt.data)
                    console.info(JSON.stringify(evt.data));
                    if(obj.type=='onLogin'){
                        //$('#QEM').empty();
                        qrcode.clear();

                        console.info("http://"+document.location.host+"/todo/api/todo/login?tocken="+obj.tocken);
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
                    }else if(obj.type=="login-ok"){
                        $scope.userName=obj.account;
                        $scope.username=obj.account;
                        $scope.password=obj.password;

                        $scope.status="正在登录";
                        $scope.$apply();
                        $("form").submit();
                        setTimeout(function(){
                           //  websocket.onclose();
                           // websocket = new
                           //   SockJS("http://"+document.location.host+"/todo/api/websocket?account="+obj.account);
                            $('#QEM_Modal').modal({
                                closable:false
                            }).modal('hide');
                            window.location.reload()
                        }, 1000);

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

                function uuid() {
                    var s = [];
                    var hexDigits = "0123456789abcdef";
                    for (var i = 0; i < 36; i++) {
                        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
                    }
                    s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
                    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
                    s[8] = s[13] = s[18] = s[23] = "-";
                    var uuid = s.join("");
                    return uuid;
                }

            }]);
})(angular);
