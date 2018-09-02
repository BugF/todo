(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('listController',
        ['$scope', 'menuManager', 'taskService', 'listService', '$routeParams','$location',
            function ($scope, menuManager, taskService, listService, $routeParams,$location) {
                $scope.menuServer = menuManager;
                $scope.menuServer.showActive('menu_all');
                $scope.canUpdate = false;
                $scope._thisList =
                    angular.copy($scope.menuServer.listMenuMap[$routeParams.id]);
                var oldTitle = angular.copy($scope._thisList.title);
                // $('#_listTitle').dblclick(function (e) {
                //     $('#_listTitle').attr("readonly",false);
                //     $('#_listTitle').css("background-color", 'rgba(223, 234, 227, 0.2)');
                //     $('#_listTitle').focus();
                //     $scope.$apply();
                // })
                getTasks();

                function getTasks() {
                    var a = {
                        setId: $routeParams.id,
                        beOver: false,
                        beDelete: false
                    };
                    $scope.listTasks = [];
                    taskService.list(a).then(
                        function (data) {
                            if (data.status === 'true') {
                                $scope.listTasks = data.datas;
                            }
                        }
                    )
                }

                $scope.listTasks = [];

                function createTask() {
                    if($scope.title==null||$scope.title==='')
                        return;
                    isCreating=true;
                    var a = {
                        setId: $routeParams.id,
                        title: $scope.title
                    };
                    taskService.create(a).then(
                        function (data) {
                            if (data.status === 'true') {
                                $scope.listTasks.push(data.datas);
                            }
                            $scope.title=null;
                            isCreating=false;
                        }
                    )
                }

                $scope.addTask = function () {
                    createTask();
                };

                function getTitle(p) {
                    var i = 0;
                    var titlePro = p;
                    var t = titlePro;
                    while ($.inArray(t, $scope.menuServer.listMenuTitleArr) >= 0) {
                        i++;
                        t = titlePro + i;
                    }
                    return t;
                }

                $scope.db = function () {
                    $scope.canUpdate = true;
                    setTimeout(function () {
                        $('#_listTitle')[0].focus();
                    }, 10);
                };
                $scope.listEnterEvent = function (e) {
                    var keycode = window.event ? e.keyCode : e.which;
                    if (keycode === 13) {
                        updateList();
                    }
                };
                var isCreating=false;
                $scope.listTaskEnterEvent = function (e) {
                    var keycode = window.event ? e.keyCode : e.which;
                    if (keycode === 13) {
                        if(!isCreating)
                        createTask();
                    }
                };

                function updateList() {
                    var title = angular.copy($scope._thisList.title);
                    if (oldTitle === title)
                        $scope.canUpdate = false;
                    else {
                        var t = getTitle(title);
                        $scope._thisList.title = t;
                        listService.update($scope._thisList).then(
                            function (data) {
                                if (data.status === 'true') {
                                    var i = $.inArray(oldTitle, $scope.menuServer.listMenuTitleArr)
                                    $scope.menuServer.listMenuTitleArr.splice(i, 1);
                                    $scope.menuServer.listMenuTitleArr.push(t);
                                    $scope.menuServer.listMenuMap[$routeParams.id]
                                        = angular.copy($scope._thisList);
                                } else {
                                    $scope._thisList.title = oldTitle;
                                }
                                oldTitle = angular.copy($scope._thisList.title);
                                $scope.canUpdate = false;
                            }
                        );

                    }
                }

                $scope.onBlur = function () {
                    updateList();
                };

                $scope.todayData = [];

                function getThisList() {
                    listService.getById($routeParams.id).then(
                        function (obj) {
                            if (obj.status === 'true' && null != obj.datas) {
                                $scope._thisList = obj.datas;
                            }
                        }
                    );
                }

                function listTask() {
                    taskService.listByUser('flx').then(
                        function (value) {
                        }
                    );
                }

                $scope.A1 = { name: 'a1'    };

                $scope.removeItem = function (id) {
                    // $('#' + id).transition('fade down');
                    $('.ui.successMsg.sidebar')
                        .sidebar('setting', 'transition', 'overlay')
                        .sidebar({
                            onShow: function () {
                                setTimeout(function () {
                                    $('.ui.successMsg.sidebar').sidebar('hide')
                                }, 300);
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
                $scope.deleteList=function(){
                    var a={
                        id:$routeParams.id
                    }
                    listService.delete(a).then(
                        function (data) {
                            if(data.status=='true'){
                                var i = $.inArray($routeParams.id, $scope.menuServer.listMenuIdArr)
                                $scope.menuServer.listMenuIdArr.splice(i, 1);
                                var i2 = $.inArray($scope._thisList.title, $scope.menuServer.listMenuTitleArr)
                                $scope.menuServer.listMenuTitleArr.splice(i2, 1);
                                delete $scope.menuServer.listMenuMap[$routeParams.id];
                                console.info("i=="+i);
                                console.info("length=="+$scope.menuServer.listMenuIdArr.length);
                                var l=$scope.menuServer.listMenuIdArr.length;
                                if(l===0){
                                    goTo();
                                }else {
                                    var s= $scope.menuServer.listMenuIdArr[i<l?i:--i];
                                    goToList(s)
                                }


                            }

                        }
                    );
                };
                $scope.openTaskDetail=function(obj){
                    $('#task_page').css("left","400px");
                    $('#list_page').css("right","400px");
                };
                $scope.deleteTask=function (i) {
                    var a={
                        id:angular.copy($scope.listTasks[i].id)
                    };
                    taskService.delete(a).then(
                        function (data) {
                            if(data.status=='true'){
                                $scope.listTasks.splice(i,1);
                            }
                        }
                    )
                }
                function goToList(id){
                    $location.path('/list/' + id);
                }
                function goTo(){
                    $location.path('/menu_today');
                }
                $('.pointing.dropdown.button').dropdown();
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
