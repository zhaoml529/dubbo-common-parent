/**
 * Created by yyx on 2017/3/29.
 * 用户业务层
 * $http.get
 *
 * $http.head
 *
 * $http.post
	
 * $http.put
	
 * $http.delete
	
 * $http.jsonp
	
 * $http.patch
 */
app.service('goodsDetailService', ['$http', '$q', 'host',function($http, $q, host) {
	return {
	    // 编辑用户
	    editUser:function(params) {
	    	var deferred = $q.defer();
	    	$http({
                method: "put",
                data: angular.toJson(params),//JsonData = {"id":1,"value":"hello"}
                url: host+"/user/"+user.id,
                headers: { "Content-Type": "application/json" }
            }).success(function (d) { 
            	deferred.resolve(d);
            });
	    	return deferred.promise;
	    }
	}
}]);