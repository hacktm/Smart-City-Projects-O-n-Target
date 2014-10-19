angular.module('content.services',[])

.factory('content',['$http','$tagId','$range','$cityId','$withPhotos',function($http,$tagId,$range,$cityId,$withPhotos){
    return {
        getAll:function(id){
            return $http.get('http://enjoycity.busymachines.com:8080/contents/'+id+'?tagId='+$tagId+'&range='+$range+'&cityId='+$cityId,{
                headers:{
                }
            });
        },
        get:function(id){
            return $http.get('http://enjoycity.busymachines.com:8080/content/'+id+'?withPhotos='+$withPhotos,{
                headers:{
                }
            });
        }
    }
}])
