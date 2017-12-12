(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject',
                                   ['newProject.directive','ngRoute']);
    /* angular.module('OneShot.service', []);*/
    angular.module('newProject.directive', []);
   /* angular.module('OneShot.filter', []);*/
    appConfig.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                title:'首页',
                templateUrl: './html/member.html',
                controller:'memberController'
            })
            .when('/computers', {
                template: '这是电脑分类页面'
            })
            .when('/printers', {
                template: '这是打印机页面'
            })
            .otherwise({
                redirectTo: '/'
            });
    }]);
})(angular);
