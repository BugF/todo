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
                <label>帐号:<%=request.getAttribute("openid")%></label>
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
            <button type="submit" ng-click="register()" class="ui blue submit button">绑定</button>
        </div>
    </div>
</div>
</div>
     <script>
         var app = angular.module('myApp',[]);
         app.controller('myCtrl',function($scope,$http) {

             $scope.register=function () {
                 alert("sss");
                 var obj={
                     account:$scope.username,
                     pasw:$scope.password,
//                     wxOpenId:'asdasdsadsadsad'
                     wxOpenId:'<%=request.getAttribute("openid")%>'
                 };
                 $http.post("http://"+document.location.host+"/todo/api/user/register", obj)
                     .then(function (result) {
                         var data=result.data;
                         alert(JSON.stringify(data));
                         console.info(JSON.stringify(data));
                     }).catch(function (result) {
                     console.info(result);
                 });
             }

         });

     </script>

</body>
</html>