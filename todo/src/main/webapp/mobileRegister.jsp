<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>绑定</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/render/semantic.min.css">
    <script src="<%= request.getContextPath()%>/js/library/jquery.min.js"></script>
    <script src="<%= request.getContextPath()%>/render/semantic.min.js"></script>
    <script src="<%= request.getContextPath()%>/js/library/angularjs.min.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath()%>/js/library/sockjs.min.js"></script>
</head>
<body  ng-app="myApp" ng-controller="myCtrl" style="background: lavender;height: 90%;">
<div style="width: 100%;position: fixed;bottom: 30px;">
<div class="ui two column centered grid" style="padding-top: 30px;height: 100%">
    <div class="column">
        <div class="ui form">
            <div class="field">
                <label>帐号</label>
                <div class="ui left icon input">
                    <input type="text" ng-model="username" name="username" placeholder="Username">
                    <i class="user icon"></i>
                </div>
            </div>
            <div class="field">
                <label>密码</label>
                <div class="ui left icon input">
                    <input type="password" ng-model="password" name="password">
                    <i class="lock icon"></i>
                </div>
            </div>
            <button type="submit" class="ui blue submit button">绑定</button>
        </div>
    </div>
</div>
</div>
     <script type="text/javascript">
         var app = angular.module('myApp', []);
         app.controller('myCtrl', function($scope) {
             function GetRequest() {
                 var url = location.search; //获取url中"?"符后的字串
                 var theRequest = new Object();
                 if (url.indexOf("?") != -1) {
                     var str = url.substr(1);
                     var strs = str.split("&");
                     for(var i = 0; i < strs.length; i ++) {
                         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
                     }
                 }
                 return theRequest;
             }
             var datas=GetRequest();
             if(null===datas['t']||undefined===datas['t']||null===datas['openid']||undefined===datas['openid']){

                 window.opener=null;

                 window.close();
             }else{
                 $('#canLogin').click(
                     function () {
                         var as={
                             tocken:datas['tocken'],
                             openid:datas['openid'],
                             type:'canLogin'
                         }
                         $.ajax({
                             type:"POST",
                             url:"api/todo/login",
                             dataType: "json",
                             contentType : 'application/json',
                             data : JSON.stringify(as),

                             success:function(data){
                                 alert(JSON.stringify(data));
                                 //  $("#msg").html(decodeURI(data));
                             }   ,
                             error: function(){
                                 //请求出错处理
                             }
                         });

                     }
                 )
             }



         });

     </script>

</body>
</html>