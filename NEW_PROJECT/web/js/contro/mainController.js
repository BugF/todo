(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('mainController', ['$scope', 'menuManager', function ($scope, menuManager) {
        $scope.menuServer = menuManager;
       /* $scope.menuServer.showActive('menu_today')
     */
        $('.sidebar.firstMenu') .sidebar('setting', 'transition', 'push').sidebar('setting', 'closable', false).sidebar('setting', 'dimPage', false).sidebar('show');
        $('.ui.sidebar.big') .sidebar('setting', 'transition', 'push').sidebar('setting', 'closable', false).sidebar('setting', 'dimPage', false).sidebar('show');
        /*$('.ui.sidebar') .sidebar('setting', 'transition', 'overlay').sidebar('show');*/
        $scope.addTodoModal={};
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
        $scope.finishItem = function (id) {
             $('#' + id).transition('fade left');
            
        }
        $scope.gotoTransh=function(){
            $('.shape').shape('flip right');
        }
       // $('.shape').shape('flip up');
        $scope.rightShow = function () {
            $('.ui.sucessMsg.sidebar')
                .sidebar('setting', 'transition', 'overlay')
                .sidebar('toggle');
        }
    }]);
})(angular);
