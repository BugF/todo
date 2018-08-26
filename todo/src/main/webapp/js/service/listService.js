(function (angular) {
    'use strict';
    var todo_task = angular.module('newProject.service');
    todo_task.factory('listService', ['$http', '$q', function ($http, $q) {
        return {
            create: function (title) {
                var url = "api/list/create";
                var defered = $q.defer();
                $http.post(url,title)
                    .then(function (result) {  //正确请求成功时处理
                        console.info(result);

                        defered.resolve(result.data);
                    }).catch(function (result) { //捕捉错误处理
                    console.info(result);
                    //alert(result.data.Message);
                    defered.reject(result);
                });
                return defered.promise;
            },
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
            },update: function (obj) {
                var url = "api/list/update";
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
            listAll: function () {
                var url = "api/list/listAll";
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
            },getById: function (id) {
                var url = "api/list/getById";
                var defered = $q.defer();
                $http.post(url,id)
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
