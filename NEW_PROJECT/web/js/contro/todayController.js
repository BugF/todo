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
            // $('#' + id).transition('fade down');
            $('.ui.successMsg.sidebar')
                .sidebar('setting', 'transition', 'overlay')
                .sidebar({
                    onShow: function () {
                        setTimeout(function(){$('.ui.successMsg.sidebar').sidebar('hide')},300)
                        
                    },
                    onChange: function () {
                      //  alert('sss')
                    },
                    onHidden: function () {
                        $('#' + id).transition('fade right');
                    }
                }).sidebar('show');
        }
        
        $scope.rightShow = function () {

            $('.ui.sucessMsg.sidebar')
                .sidebar('setting', 'transition', 'overlay')
                .sidebar('toggle');
        }
    }]);
})(angular);
