
(function (angular) {
    'use strict';
    var newProjectDirective = angular.module('newProject.directive');
    newProjectDirective.directive('repeatFinish', [
        function () {
            return {
                link: function (scope, element, attrs) {
                     console.log("****************ng-repeat"+scope.$index);
                    if(scope.$last == true){
                        console.log('*****************ng-repeat执行完毕');
                        scope.$eval( attrs.repeatFinish )
                    }
                }
            };
        }]);
})(angular, jQuery);