angular.module('category.services',[])

.factory('category',['$http',function($http){
    return {
        getAll:function(){
            return $http.get('http://enjoycity.busymachines.com:8080/categories?withPhotos=false',{
                headers:{
                }
            });
        },
        get:function(id){
            return $http.get('http://enjoycity.busymachines.com:8080/categories/'+id,{
                headers:{
                }
            });
        }
    }
}])
