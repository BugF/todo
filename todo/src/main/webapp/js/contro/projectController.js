(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('projectController',
        ['$scope', 'menuManager','taskService',
            function ($scope, menuManager,taskService) {
        $scope.menuServer = menuManager;
        $scope.menuServer.showActive('menu_project');
        console.info(JSON.stringify($scope.menuServer));
        $scope.todayData = [];
        listTask();
        function listTask() {
            taskService.listByUser('flx').then(
                function (value) {  }
            );
        }
        $scope.A1={
            name:'a1'
        };
        
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
        };
        $scope.finishItem = function (id) {
             $('#' + id).transition('fade left');
        }
        /*$scope.gotoTransh=function(){
            $('.shape').shape('flip right');
        }
       // $('.shape').shape('flip up');
        $scope.rightShow = function () {
            $('.ui.sucessMsg.sidebar')
                .sidebar('setting', 'transition', 'overlay')
                .sidebar('toggle');
        }*/
    }]);
})(angular);
