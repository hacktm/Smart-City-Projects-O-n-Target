angular.module('content.controllers',[])
.controller('ContentViewController',['$scope','content',function($scope,content){

    content.getAll().success(function(data){
        $scope.content=data;
        console.log ($scope.content);
    });

}])