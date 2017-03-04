/**
 * Created by fangf on 2016/5/22.
 */
app.controller("AppCtrl",function ($scope, $state, SweetAlert) {
    $scope.app = {
        name:"后台管理系统"
    };
    $scope.$on('$stateChangeStart',
        function(event, toState, toParams, fromState, fromParams){
            /*if (toState.name!=='signin' && !sessionStorage.getItem('token')) {
                event.preventDefault();
                $state.go('signin');
            }*/
    	console.log('AppCtrl-', toState);
    	if(toState.name.toLowerCase().indexOf('list') || toState.name.toLowerCase().indexOf('page'))
    		// 分页参数初始化
    		$scope.maxSize = 5;			// 页面上可选页数范围
    	    $scope.currentPage = 1;		// 当前页
    	    $scope.itemsPerPage = 2;	// 每页显示多少条数据
    	});
    $scope.welcomePage = function () {
    	SweetAlert.swal("提示", "Welcome!", "success");
    }
});