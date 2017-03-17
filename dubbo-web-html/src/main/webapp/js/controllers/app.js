/**
 * Created by fangf on 2016/5/22.
 */
app.controller("AppCtrl",function ($scope, $state, $cookieStore, SweetAlert, subject) {
	/*$http({
        method: "get",
        url: host+"/loginCtrl/checkLogin"
    }).success(function (d) { 
    	if (d.statusCode==200) {
    		$scope.totalItems = d.totalCount;
        	$scope.userList = d.data;
        } else {
        	$('.table').dataTable();
        }
    });*/
    $scope.app = {
        name:"后台管理系统"
    };
    $scope.$on('$stateChangeStart',
        function(event, toState, toParams, fromState, fromParams){
    		alert("$stateChangeStart");
            if (toState.name!=='login' && !subject.isAuthenticated()) {
            	alert("没有认证，跳转到login");
                event.preventDefault();
                $state.go('login');
            }
	    	if(toState.name.toLowerCase().indexOf('list') > 0 || toState.name.toLowerCase().indexOf('page') > 0) {
	    		// datatable分页参数初始化
	    		$scope.maxSize = 5;			// 页面上可选页数范围
	    		$scope.currentPage = 1;		// 当前页
	    		$scope.itemsPerPage = 2;	// 每页显示多少条数据
	    	}
    });
    $scope.welcomePage = function () {
    	SweetAlert.swal("提示", "Welcome!", "success");
    }
});