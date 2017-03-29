/**
 * Created by zml on 2017/3/29.
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
app.service('userService', ['$http', '$q', 'host',function($http, $q, host) {
	return {
		// 分页列表
	    listPage:function(params) {
	        var deferred = $q.defer();
	        // $http方法会返回一个promise对象
	        var promise = $http({
	            method: "post",
	            data: angular.toJson(params),//JsonData = {"id":1,"value":"hello"}
	            url: host+"/listUser"
	        }).success(function (d) {
	        	deferred.resolve(d);
            });
	        /*
	         * 给promise的then方法传递了两个处理函数，分别处理当promise被执行的时候以及promise被拒绝的时候所要进行的操作
	         * 也可以使用success和error回调代替，如promise.success(function(data, status, headers, config){});promise.error(function(data, status, headers, config){});
	         * then()函数接收的resp（响应对象）包含5个属性：　
			 *	1. data（字符串或对象）：响应体
			 *	2. status:相应http的状态码,如200
			 *	3. headers(函数)：头信息的getter函数，可以接受一个参数，用来获取对应名字的值
			 *	4. config(对象)：生成原始请求的完整设置对象
			 *	5. statusText:相应的http状态文本，如"ok"
	         */
	        /*
	        promise.then(
	        	// 通讯成功的处理函数
	        	function(answer){
	        		//在这里可以对返回的数据集做一定的处理,再交由controller进行处理
	        		deferred.resolve(answer.data);
	        	},
	        	// 通讯失败的处理函数
	        	function(error){
	        		// 可以先对失败的数据集做处理，再交由controller进行处理
	        		// 在此之前已经由httpInterceptor统一处理错误，此处方法可以省略，此处只是展示
	        		console.dir("error:"+error);
	        		deferred.reject(error);
	        });
	        
	        //返回promise对象，交由controller继续处理成功、失败的业务回调
	        */
	        return deferred.promise;
	    },
	    // 添加用户
	    addUser:function(params) {
	    	var deferred = $q.defer();
	    	$http({
                method: "post",
                data: angular.toJson(params),//JsonData = {"id":1,"value":"hello"}
                url: host+"/user",
                headers: { "Content-Type": "application/json" }
            }).success(function (d) { 
            	deferred.resolve(d);
            });
	    	return deferred.promise;
	    },
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
	    },
	    // 删除用户
	    delUser:function(id) {
	    	var deferred = $q.defer();
	    	$http({
                method: "delete",
                url: host+"/user/"+id,
            }).success(function (d) { 
            	deferred.resolve(d);
            });
	    	return deferred.promise;
	    },
	    lockUser:function(id, status) {
	    	var deferred = $q.defer();
	    	$http({
                method: "get",
                url: host+"/user/"+id+"/lock/"+status,
            }).success(function (d) { 
            	deferred.resolve(d);
            });
	    	return deferred.promise;
	    }
	    
	}
}]);