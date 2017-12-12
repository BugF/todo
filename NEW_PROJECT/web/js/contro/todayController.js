(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('todayController', ['$scope', 'menuManager', function ($scope, menuManager) {
        $scope.menuServer = menuManager;
        $scope.menuServer.showActive('menu_today')
        console.info(JSON.stringify($scope.menuServer));
        $scope.todayData = [
            {
                id: 'aa'
            }, {
                id: 'bb'
            }, {
                id: 'cc'
            }, {
                id: 'dd'
            }
        ];
        $scope.removeItem = function (id) {
            $('#' + id).transition('fade down');
            $('.ui.successMsg.sidebar')
                .sidebar('setting', 'transition', 'overlay')

            .sidebar('toggle')
            .sidebar('toggle');
        }
        $scope.rightShow = function () {

            $('.ui.sucessMsg.sidebar')
                .sidebar('setting', 'transition', 'overlay')
                .sidebar('toggle');
        }
    }]);
})(angular);
