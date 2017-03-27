/**
 * Created by fangf on 2016/5/22.
 */
app.controller("AppCtrl",function ($scope, $state, $cookieStore, SweetAlert, angularPermission) {
    $scope.app = {
        name:"后台管理系统"
    };
    
    // 接收$stateChangeStart事件
    $scope.$on('$stateChangeStart',
        function(event, toState, toParams, fromState, fromParams){
            if (toState.name!=='login' && !sessionStorage.getItem('token')) {
                event.preventDefault();
                alert("没有认证，跳转到login");
                $state.go('login');
                return;
            }
            var permission = toState.permission;
            if(angular.isString(permission) && !angularPermission.hasPermission(permission)){
              // here I redirect page to '/unauthorized', you can edit it
        	  alert("没有权限访问!");
              $location.path('/unauthorized');
            }
    });
    
    $scope.welcomePage = function () {
    	SweetAlert.swal("提示", "Welcome!", "success");
    }
});