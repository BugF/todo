(function (angular) {
    'use strict';
    var appConfig = angular.module('newProject');
    appConfig.controller('memberController', ['$scope', function ($scope) {
        $scope.rightShow = function () {

            $('.ui.sidebar')
                .sidebar('setting', 'transition', 'overlay')

            .sidebar('toggle');
        }
        $scope.closeDiv=function(){
            $('#balckBoard')
                .transition('fly right');
            $('.rightDataItem')
                .transition('fly left');
        }
        $scope.rightShow2 = function () {
            /*$('#editMember')
                .transition('hide'); */
            
            $('#balckBoard')
                .transition('fly right');
            $('.rightDataItem')
                .transition('fly left');
        }
        $scope.nowDate=new Date();

        init($scope.nowDate);

    
        
        function init(obj) {
            var weeks=[];        
            var dayLength=getDayCount(obj);
            var day=getFirstDateDay(obj);
            var days=[];
            for(var a=1;a<=dayLength;a++){
                days.push(a);
            }
            for(var a=0;a<day;a++){
                days.unshift(0)
            }
            var lackDays=7-(days.length%7==0?7:days.length%7);
            for(var a=0;a<lackDays;a++){
                days.push(0)
            }
            var weekCount=days.length/7;
            for(var a=0;a<weekCount;a++){
                var week=days.slice(a*7,a*7+7);
                weeks.push(week);
                console.info(JSON.stringify(week));
            }
            $scope.weeks=angular.copy(weeks);
        }

        $scope.repeatFinish=function(){
            $('.seven.column.row.transition.hidden').transition('fade');
        }
        $scope.lastMonth=function(){
            $('.seven.column.row.transition.hidden').transition('fade');
            var date=new Date($scope.nowDate);
            var month=date.getMonth()+1;
            var year=date.getFullYear();
            if(month>1){
                month=month-1;
            }else{
                year=year-1;
                month=12;
            }
            $scope.nowDate=new Date(year+"-"+month+"-1");
            init(year+"-"+month+"-1")
        }
        $scope.nextMonth=function(){
            $('.seven.column.row.transition.hidden').transition('fade');
            var date=new Date($scope.nowDate);
            var month=date.getMonth()+1;
            var year=date.getFullYear();
            if(month<12){
                month=month+1;
            }else{
                year=year+1;
                month=1;
            }
            $scope.nowDate=new Date(year+"-"+month+"-1");
            init(year+"-"+month+"-1");
        }

        function getDayCount(str) {
            var date;
            if (null == str || $.isEmptyObject(str)) {
                date = new Date();
            }
            date = new Date(str);

            date.setMonth(date.getMonth()+1);
            date.setDate(0);
            return date.getDate();
        }
        function getFirstDateDay(str) {
            var date;
            if (null == str || $.isEmptyObject(str)) {
                date = new Date();
            }
            date = new Date(str);

            //date.setMonth(date.getMonth()+1);
            date.setDate(1);
            return date.getDay();
        }

    }]);
})(angular);
