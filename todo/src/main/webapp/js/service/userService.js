(function (angular) {
    'use strict';
    var todo_task = angular.module('newProject.service');
    todo_task.factory('userService', ['$http', '$q', function ($http, $q) {
        return {
            isLogin: function () {
                var url = "api/user/isLogin";
                var defered = $q.defer();
                $http.get(url)
                    .then(function (result) {  //正确请求成功时处理
                        console.info(result);
                        //alert(result.data);
                        defered.resolve(result.data);
                    }).catch(function (result) { //捕捉错误处理
                    console.info(result);
                    //alert(result.data.Message);
                    defered.reject(result);
                });
                return defered.promise;
            },getUserLoginInfo: function (obj) {
                var url = "api/user/getUserLoginInfo";
                var defered = $q.defer();
                $http.post(url, obj)
                    .then(function (result) {  //正确请求成功时处理
                        console.info(result);
                        //alert(result.data);
                        defered.resolve(result.data);
                    }).catch(function (result) { //捕捉错误处理
                    console.info(result);
                    //alert(result.data.Message);
                    defered.reject(result);
                });
                return defered.promise;
            }
        }
            ;
    }])
})(angular);
