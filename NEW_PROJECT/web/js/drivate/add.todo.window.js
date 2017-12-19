(function (angular) {
        'use strict';
        var newProjectDirective = angular.module('newProject.directive');
        newProjectDirective.directive('addTodoWindow', [
        function () {
                return {
                    restrict: 'EA',
                    templateUrl: 'js/drivate/addTodoWindow.html',
                    replace: true,
                    scope: {
                        conf: "="
                    },
                    link: function (scope, element, attrs) {

                        scope.conf.showDiv = function () {
                            $('.ui.modal').modal('show');
                        }
                       
                        $('#newTodoTime').datetimepicker ({
                            minDate: new Date(),
                            controlType: 'select',
                            oneLine: true,
                            dateFormat: 'yy-mm-dd', 
                            timeFormat: 'hh:mm tt'
                        });
                }
            };
        }]);
})(angular, jQuery);
