(function (angular) {
        'use strict';
        var newProjectDirective = angular.module('newProject.directive');
        newProjectDirective.directive('a1', [
        function () {
                return {
                    restrict: 'EA',
                    templateUrl: 'js/drivate/a1.html',
                    replace: true,
                    scope: {
                        conf: "="
                    },
                    controller:function($scope){
                        $scope.A2={
                            name:"a2",
                            init:function(){
                                alert("a2")
                            }
                        }
                   
                    },
                    link: function (scope, element, attrs,sccc) {
                        alert(JSON.stringify(scope.A2))
                        scope.A2.name="a5";
                       
                    }
            };
        }]);
})(angular, jQuery);