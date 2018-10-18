<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <style type="text/css">
        body {
            background-color: #DADADA;
        }
        body > .grid {
            height: 100%;
        }
        .image {
            margin-top: -100px;
        }
        .column {
            max-width: 600px;

        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/render/semantic.min.css">
    <script src="<%= request.getContextPath()%>/js/library/qrcode.min.js"></script>
    <script src="<%= request.getContextPath()%>/js/library/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/render/semantic.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/js/library/sockjs.min.js"></script>
    <script src="<%= request.getContextPath()%>/js/library/angularjs.min.js"></script>
</head>
<body ng-app="myApp" ng-controller="myCtrl">
<div class="ui middle aligned center aligned grid">
    <div class="column" style="width:1000px ;    background: beige;">
        <div class="ui two column very relaxed stackable grid">
            <div class="column">
                <form action="<%= request.getContextPath()%>/api/j_spring_security_check" method="post" class="ui form">
                    <div class="field" align="left">
                        <label>账号</label>
                        <div class="ui left icon input">
                            <input type="text" ng-model="username" name="username" placeholder="Username">
                            <i class="user icon"></i>
                        </div>
                    </div>
                    <div class="field"  align="left">
                        <label>密码</label>
                        <div class="ui left icon input">
                            <input type="password" ng-model="password" name="password">
                            <i class="lock icon"></i>
                        </div>
                    </div>
                    <button type="submit" class="ui blue submit button">登陆</button>
                    <div class="field"  align="left" style="padding-top: 7px;">
                    <label style="color: red"><%=null==request.getAttribute("msg")?"":request.getAttribute("msg")%></label>
                    </div>
                </form>
            </div>
            <div class="middle aligned column" align="center">
                <div id="QEM" style="text-align: center" align="center"></div>
                <div>{{status}}</div>
            </div>
        </div>
        <div class="ui vertical divider">Or</div>
    </div>
</div>
</body>

<script>

    var app = angular.module('myApp', []);
    app.controller('myCtrl', function ($scope) {
        $scope.status = "扫描上方二维码";
        $scope.loading = false;
        var qrcode = new QRCode(
            document.getElementById("QEM"), {
                width: 250,//设置宽高
                height: 250
            });
        $scope.uuid = uuid();
        var websocket = new SockJS("http://" + document.location.host + "/todo/api/websocket?account=" + $scope.uuid);
        qrcode.clear();
        qrcode.makeCode(
            "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=wx9fb5d2c14742fa1f&" +
            "redirect_uri=http%3a%2f%2f" + document.location.host + "%2ftodo%2fapi%2fuser%2flogin" +
            "&response_type=code&scope=snsapi_base&state=" + $scope.uuid
            + "#wechat_redirec");
        //qrcode.makeCode("http://"+document.location.host+"/todo/api/user/login?tocken="+$scope.uuid);
        jQuery('.ui.login.modal').modal({closable: false})
            .modal('show')
        ;

        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;
        function onOpen(openEvt) {
            console.info(JSON.stringify(openEvt));
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
                jQuery('#QEM_Modal').modal({
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
                jQuery("form").submit();
                setTimeout(function(){
                    //  websocket.onclose();
                    // websocket = new
                    //   SockJS("http://"+document.location.host+"/todo/api/websocket?account="+obj.account);
                    jQuery('#QEM_Modal').modal({
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

    })
    ;


</script>
</html>