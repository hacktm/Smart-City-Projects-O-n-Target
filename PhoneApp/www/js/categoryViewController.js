angular.module('category.controllers',[]).controller('CategoryViewController',['$scope','category','$ionicSlideBoxDelegate',function($scope,category,$ionicSlideBoxDelegate){

    category.getAll().success(function(data){
        $scope.categories=data;
        $ionicSlideBoxDelegate.update();
        console.log ("Categories received from server: ");
        data.map(function(item){console.log(item);});
    });
}]);