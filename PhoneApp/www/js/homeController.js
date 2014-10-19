angular.module('home.controllers',[])


.controller('HomeController',['$scope','$state',function($scope, $state){

   console.log('Loaded main page.')

   $scope.explore=function(){
   		console.log('Navigate to explore category menu.')
   		$state.go("categoryView");
   };

   $scope.feelings=function(){
   		console.log('Navigate to feelings menu.')
   		$state.go("gmapsView");
   };


}])