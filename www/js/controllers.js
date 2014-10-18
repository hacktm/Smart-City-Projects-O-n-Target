angular.module('activityApp.controllers',[]).controller('ActivityListController',['$scope','Activity',function($scope,Activity){

    Activity.getAll().success(function(data){
        $scope.items=data.results;
    });

    $scope.onItemDelete=function(item){
        Activity.delete(item.objectId);
        $scope.items.splice($scope.items.indexOf(item),1);
    }

}]);

angular.module('activityApp.controllers').controller('ActivityCreationController',['$scope','Activity','$state',function($scope,Activity,$state){

    $scope.activity={};

    $scope.create=function(){
        Activity.create({content:$scope.activity.content}).success(function(data){
            $state.go('activities');
        });
    }
}]);

angular.module('activityApp.controllers').controller('ActivityEditController',['$scope','Activity','$state','$stateParams',function($scope,Activity,$state,$stateParams){

    $scope.activity={id:$stateParams.id,content:$stateParams.content};

    $scope.edit=function(){
        Activity.edit($scope.activity.id,{content:$scope.activity.content}).success(function(data){
            $state.go('activities');
        });
    }

}]);