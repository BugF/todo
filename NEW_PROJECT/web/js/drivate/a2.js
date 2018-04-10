(function (angular) {
        'use strict';
        var newProjectDirective = angular.module('newProject.directive');
        newProjectDirective.directive('a2', [
        function () {
                return {
                    restrict: 'EA',
                    templateUrl: 'js/drivate/a2.html',
                    replace: true,
                    scope: {conf:"="},
                    request:"^a1",
                    link: function (scope, element, attrs) {
                        alert(JSON.stringify(scope.conf));
                        scope.conf.init()
                    
                }
            };
        }]);
})(angular, jQuery);