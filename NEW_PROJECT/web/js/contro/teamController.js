(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('teamController', ['$scope', 'menuManager', function ($scope, menuManager) {
        $scope.menuServer = menuManager;
        $scope.menuServer.showActive('menu_team');
        var editor = new Simditor({
            textarea: $('#editor'),
            upload: true,
            
            toolbar: ['mark', 'title', 'bold', 'italic', 'underline', 'strikethrough', 'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table','image', ],
            autosavePath: '/js/1',
            pasteImage: true
        });
        
        $scope.do=function(){
            console.info(editor.sync());
            console.info(editor.getValue());
        }
        
    }]);
})(angular);
