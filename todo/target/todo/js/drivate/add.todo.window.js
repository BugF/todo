(function (angular) {
    'use strict';
    var newProjectDirective = angular.module('newProject.directive');
    newProjectDirective.directive('addTodoWindow', ['taskService',
        function (taskService) {
            return {
                restrict: 'EA',
                templateUrl: 'js/drivate/addTodoWindow.html',
                replace: true,
                scope: {
                    conf: "="
                },
                link: function (scope, element, attrs) {
                    scope.task = {};
                    $('#newTodoTime').datetimepicker({
                        minDate: new Date(),
                        controlType: 'select',
                        oneLine: true,
                        dateFormat: 'yy-mm-dd',
                        timeFormat: 'hh:mm tt',
                        onSelect: function (selectedDateTime) {
                            if (!$.isEmptyObject(selectedDateTime))
                                scope.task.alarmTime = new Date(selectedDateTime);
                        }
                    });
                    scope.newTypes=[
                        {
                            title:"任务",
                            icon:"th list",
                            id:"task"
                        },{
                            title:"项目",
                            icon:"folder",
                            id:"project"
                        }];
                    scope.typeMap={
                        task:{
                            title:"任务",
                            icon:"th list",
                            id:"task"
                        },
                        project:{
                            title:"项目",
                            icon:"folder",
                            id:"project"
                        }
                    };
                    scope.addType="task";
                    scope.conf.showDiv = function (obj) {

                        scope.task = {};
                        if (null != obj)
                            $('#newTodoTime').datetimepicker('setDate', new Date(obj));
                        else
                            $('#newTodoTime').datetimepicker('setDate', '');

                        $('#_new')
                            .modal({
                                closable: false,
                                onDeny: function () {

                                },
                                onApprove: function () {
                                    taskService.insert(scope.task).then(
                                        function (data) {

                                        }, function (error) {
                                            alert(JSON.stringify(error));

                                        }
                                    );
                                    return false;
                                }
                            })
                            .modal('show');
                    };
                    $(".dropdown").dropdown();
                }
            };
        }]);
})(angular, jQuery);
