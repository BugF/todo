(function (angular) {
    'use strict';
    var menuFactory = angular.module('newProject.service');
    menuFactory.factory('menuManager', ['$location','$http','listService',
        function ($location,$http,listService) {
        var MenuManager = function () {
                this.title = "TODAY",
                this.menus = [
                    {
                        id: 'menu_today',
                        title: '今天',
                        active: false
                    }, {
                        id: 'menu_all',
                        title: '全部',
                        active: false
                    }
                ],
                this.listMenuMap={},
                this.listMenuIdArr=[]
                this.listMenuTitleArr=[]


        }

        MenuManager.prototype = {
            constructor: MenuManager,
            setTitle: function (title) {
                this.title = title;
            },
            setListMenuIdArr:function(listMenuIdArr){
                this.listMenuIdArr=listMenuIdArr
            },
            setListMenuTitleArr:function(listMenuTitleArr){
                this.listMenuTitleArr=listMenuTitleArr
            },
            setListMenuMap:function(listMenuMap){
                this.listMenuIdArr=listMenuIdArr
            },
            goTo: function (url, replace) {
                $location.path(url);
                if (replace)
                    $location.replace();
            },
            goBack: function () {
                $window.history.back();
            },
            getMenu: function () {
                return this.menus;
            },
            // getListMenu:function(){
            //     console.info(JSON.stringify(this))
            //     var listMenuMap={};
            //     var listMenuIdArr=[];
            //     var listMenuTitleArr=[];
            //     listService.listAll().then(
            //         function (obj) {
            //             console.info(JSON.stringify(this));
            //             if(obj.status=='true'){
            //                 $.each(obj.datas,function (i,o) {
            //                     listMenuIdArr.push(o.id);
            //                     listMenuTitleArr.push(o.id);
            //                     listMenuMap[o.id]=o;
            //                 });
            //                 console.info(JSON.stringify(this));
            //                 this.listMenuIdArr=listMenuIdArr;
            //                 this.listMenuTitleArr=listMenuTitleArr;
            //                 this.listMenuMap=listMenuMap;
            //
            //                 /* console.info(JSON.stringify(listMenuIdArr));
            //                  console.info(JSON.stringify(listMenuMap));*/
            //             }
            //         }
            //     );
            //
            // },
            selectMenu: function (id) {
                angular.forEach(this.menus,
                    function (menu) {
                        if (menu.id == id)
                            menu.active = true;
                        else menu.active = false;
                    })
                /*if()*/
                $location.path('/' + id);
            },
            showActive: function (id) {
                angular.forEach(this.menus,
                    function (menu) {
                        if (menu.id == id)
                            menu.active = true;
                        else menu.active = false;
                    })
            },
            freshMenu: function () {

            }
        }
            var menuServer = new MenuManager();
            function getListMenu(){
                //console.info(JSON.stringify(this))
                var listMenuMap={};
                var listMenuIdArr=[];
                var listMenuTitleArr=[];
                listService.listAll().then(
                    function (obj) {
                        if(obj.status=='true'){
                            $.each(obj.datas,function (i,o) {
                                menuServer.listMenuIdArr.push(o.id);
                                menuServer.listMenuTitleArr.push(o.title);
                                menuServer.listMenuMap[o.id]=o;
                            });
                        }
                    }
                );

            }

        getListMenu();
        //var obj=menuServer.getListMenu();
       /* menuServer.listMenuIdArr=obj.listMenuIdArr,
            menuServer.listMenuTitleArr=obj.listMenuTitleArr,
            menuServer.listMenuMap=obj.listMenuMap*/
       console.info("init menuServer")
        return menuServer;
    }])
})(angular)
