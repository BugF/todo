(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject',
                                   ['newProject.service',
                                    'newProject.directive','ngRoute']);
    angular.module('newProject.service',[]);
    angular.module('newProject.directive', []);
   /* angular.module('OneShot.filter', []);*/
    appConfig.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/menu_calendar', {
                title:'首页',
                templateUrl: './html/member.html',
                controller:'memberController'
            })
            .when('/menu_today', {
                title:'今日',
                templateUrl: './html/today.html',
                controller:'todayController'
            })
            .otherwise({
                redirectTo: '/menu_today'
            });
    }]);
})(angular);
