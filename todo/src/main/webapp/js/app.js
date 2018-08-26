(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject',
                                   ['newProject.service',
                                    'newProject.directive','ngRoute']);
    angular.module('newProject.service',[]);
    angular.module('newProject.directive', ['newProject.service']);
   /* angular.module('OneShot.filter', []);*/
    appConfig.config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/menu_calendar', {
                title:'首页',
                templateUrl: './html/calendar.html',
                controller:'calendarController'
            })
            .when('/menu_today', {
                title:'今日',
                templateUrl: './html/today.html',
                controller:'todayController'
            }).when('/list/:id', {
                title:'列表',
                templateUrl: './html/list.html',
                controller:'listController'
            }) .when('/menu_all', {
                title:'全部',
                templateUrl: './html/all.html',
                controller:'allController'
            })
            .otherwise({
                redirectTo: '/menu_today'
            });
    }]);
})(angular);
