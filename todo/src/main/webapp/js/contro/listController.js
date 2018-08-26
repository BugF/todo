(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('listController',
        ['$scope', 'menuManager','taskService','listService','$routeParams',
            function ($scope, menuManager,taskService,listService,$routeParams) {
        $scope.menuServer = menuManager;
        $scope.menuServer.showActive('menu_all');
        $scope.canUpdate=false;
        $scope._thisList=
            angular.copy($scope.menuServer.listMenuMap[$routeParams.id]);
        var oldTitle= angular.copy($scope._thisList.title);
        // $('#_listTitle').dblclick(function (e) {
        //     $('#_listTitle').attr("readonly",false);
        //     $('#_listTitle').css("background-color", 'rgba(223, 234, 227, 0.2)');
        //     $('#_listTitle').focus();
        //     $scope.$apply();
        // })
                function getTitle(p){
                    var i=0;
                    var titlePro=p;
                    var t=titlePro;
                    while($.inArray(t,$scope.menuServer.listMenuTitleArr)>=0){
                        i++;
                        t=titlePro+i;
                    }
                    return t;
                }
        $scope.db=function(){
            $scope.canUpdate=true;
            setTimeout(function(){$('#_listTitle')[0].focus();},10);

        }
        $scope.onBlur=function(){

            var title=getTitle(angular.copy($scope._thisList.title));
            if(oldTitle==title)
                $scope.canUpdate=false;
            else{
                var t=getTitle(title);
                $scope._thisList.title=t;
                listService.update($scope._thisList).then(
                    function (data) {
                        if(data.status=='true'){
                            var i=$.inArray(oldTitle,$scope.menuServer.listMenuTitleArr)
                            $scope.menuServer.listMenuTitleArr.splice(i,1);
                            $scope.menuServer.listMenuTitleArr.push(t);

                            $scope.menuServer.listMenuMap[$routeParams.id]
                                =angular.copy($scope._thisList);
                        }else{
                            $scope._thisList.title=oldTitle;
                        }
                        oldTitle=angular.copy($scope._thisList.title);
                        $scope.canUpdate=false;
                    }
                );

            }
        }
        $scope.todayData = [];
        //listTask();

       // getThisList();
        function getThisList() {
            listService.getById($routeParams.id).then(
                function (obj) {
                    if(obj.status=='true'&&null!=obj.datas){
                        $scope._thisList=obj.datas;
                    }
                }
            );
        }
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
