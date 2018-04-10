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
                templateUrl: './html/calendar.html',
                controller:'calendarController'
            })
            .when('/menu_today', {
                title:'今日',
                templateUrl: './html/today.html',
                controller:'todayController'
            }).when('/menu_team', {
                title:'今日',
                templateUrl: './html/team.html',
                controller:'teamController'
            })
            .otherwise({
                redirectTo: '/menu_today'
            });
    }]);
})(angular);
