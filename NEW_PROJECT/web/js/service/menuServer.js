(function (angular) {
    'use strict';
    var menuFactory = angular.module('newProject.service');
    menuFactory.factory('menuManager', ['$location', function ($location) {
        var MenuManager = function () {
            this.title = "TODAY",
                this.menus = [
                    {
                        id: 'menu_today',
                        title: '今天',
                        active: false
                }, {
                        id: 'menu_calendar',
                        title: '日历',
                        active: false
                }, {
                        id: 'menu_message',
                        title: 'message',
                        active: false
                }
            ]
        }
        MenuManager.prototype = {
            constructor: MenuManager,
            setTitle: function (title) {
                this.title = title;
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
            selectMenu: function (id) {
                angular.forEach(this.menus,
                    function (menu) {
                        if (menu.id == id)
                            menu.active = true;
                        else menu.active = false;
                    })
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
        return menuServer;
    }])
})(angular)
