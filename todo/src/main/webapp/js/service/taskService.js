(function (angular) {
    'use strict';
    var todo_task = angular.module('newProject.service');
    todo_task.factory('taskService', ['$http', '$q', function ($http, $q) {
        return {
            insert: function (obj) {
                var url = "api/task/insert";
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
            },create: function (obj) {
                var url = "api/task/create";
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
            },
            listByUser: function (obj) {
                var url = "api/task/listByUser";
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
            },list: function (obj) {
                var url = "api/task/list";
                var defered = $q.defer();
                $http.post(url,obj)
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
            },delete: function (obj) {
                var url = "api/task/delete";
                var defered = $q.defer();
                $http.post(url,obj)
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
            },
            loginUser: function () {
                var url = "api/user/loginUser";
                var defered = $q.defer();
                $http.post(url)
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
