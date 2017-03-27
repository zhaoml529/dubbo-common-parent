/**
 * Created by fangf on 2016/5/19.
 */
app.controller('SigninCtrl',function ($scope,$state,host,SweetAlert,$http,angularPermission) {
    $scope.submit = function () {
    	console.log(angular.toJson($scope.user));
    	if (!$scope.user.username) {
        	return;
        }
    	if (!$scope.user.password) {
    		return;
    	}
    	
        $http({
            method: "post",
            url: host+"/login",
            data: {"username":$scope.user.username, "password":$scope.user.password},
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
        }).success(function (d) { 
        	if (d.statusCode==200) {
        		sessionStorage.token = d.data.token;
        		angularPermission.setPermissions(d.data.permissions);
        		SweetAlert.swal("登录成功","欢迎登录，"+d.data,'success');
        		$state.go('index');
            } else {
            	SweetAlert.swal("登录失败",d.message,'error');
            }
        });
        
    }
});