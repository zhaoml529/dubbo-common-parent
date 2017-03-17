/**
 * Created by fangf on 2016/5/19.
 */
app.controller('SigninCtrl',function ($scope,$state,host,SweetAlert,$http,subject,usernamePasswordToken) {
    $scope.submit = function () {
    	console.log(angular.toJson($scope.user));
    	if (!$scope.token.username) {
        	return;
        }
    	if (!$scope.token.password) {
    		return;
    	}
    	$scope.token = usernamePasswordToken;
    	//var token = new UsernamePasswordToken("admin", "123");
    	//$scope.token = usernamePasswordToken;
    	console.dir(usernamePasswordToken);
        /*$http({
            method: "post",
            url: host+"/loginCtrl/signin",
            data: {"userName":$scope.user.userName, "passwd":$scope.user.passwd, "rememberMe":false},
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).success(function (d, response, headers) { 
        	console.log(d);
        	console.dir(response);
        	console.dir(headers);
        	if (d.statusCode==200) {
        		SweetAlert.swal("登录成功","欢迎登录，"+d.data,'success');
        		$state.go('index');
            } else {
            	SweetAlert.swal("登录失败",d.message,'error');
            }
        });*/
        
        subject.login($scope.token).then(function() {
        	alert("login: go index");
        	$state.go('index');
		}, function(data) {
			if (d.statusCode==200) {
        		SweetAlert.swal("登录成功","欢迎登录，"+d.data,'success');
        		//$state.go('index');
            } else {
            	SweetAlert.swal("登录失败",d.message,'error');
            }
		});
    }
});