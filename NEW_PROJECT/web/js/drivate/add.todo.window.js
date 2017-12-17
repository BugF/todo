(function (angular) {
    'use strict';
    var newProjectDirective = angular.module('newProject.directive');
    newProjectDirective.directive('addTodoWindow', [
        function () {
            return {
                restrict: 'E',
                templateUrl: 'jd/drivate/addTodoWindow.html',
                replace: true,
                scope: {
                    conf: "="
                },
                link: function (scope, element, attrs) {
                    scope.conf.addTodoModal.showDiv=function(){
                        $('.ui.modal').modal('show');
                    }
                }
            };
        }]);
})(angular, jQuery);
