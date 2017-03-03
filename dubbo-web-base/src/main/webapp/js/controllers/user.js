/**
 * Created by zml on 2017/3/2.
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
app.controller('userCtrl',function ($scope,$modal,$http,host,$state,SweetAlert) {
	alert($state.current.name);
	// 获取列表
    $http.get(host+'/user').success(function (d) {
        if (d.statusCode=='OK') {
        	$scope.userList = d.data;
        } 
        if (d.data.length==0) {
        	$('.table').dataTable();
        }
    });
    //$scope.maxSize = 3;			// 页面上可选页数范围
    $scope.totalItems = 100;	// 总共有多少数据
    //$scope.currentPage = 4;		// 当前页
    $scope.pageChanged = function() {
        console.dir('Page changed to: ' + $scope.currentPage);
        console.dir('$scope: ' + $scope.itemsPerPage);
    };
    
    // 添加
    $scope.add = function () {
        $modal.open({
            templateUrl: 'tpls/user/user-add.html',
            controller: function($scope,$modalInstance){
                $scope.user = {};
                $scope.ok = function(){
                    $scope.check = true;
                    if (!$scope.user.userName) {
                    	return;
                    }
                    $http({
                        method: "post",
                        data: angular.toJson($scope.user),//JsonData = {"id":1,"value":"hello"}
                        url: host+"/user",
                        headers: { "Content-Type": "application/json" }
                    }).success(function (d) { 
                    	if (d.statusCode=='OK') {
                    		SweetAlert.swal("增加成功", "新增用户“"+$scope.user.userName+"”", "success");
                        } else {
                        	SweetAlert.swal("增加失败", d.message, "error");
                        }
                        $modalInstance.close();
                        $state.reload();
                    }).error(function(error){
                    	console.log(error);
                    });
                };
                $scope.cancel = function(){
                    $modalInstance.dismiss();
                }
            },
            size: 'md'
        });
    };
    
    // 编辑
    $scope.edit = function (id) {
    	var user = $scope.userList[id];
        $modal.open({
            templateUrl: 'tpls/user/user-detail.html',
            controller: function($scope,$modalInstance){
                $scope.user = user;
                $scope.ok = function(){
                    $scope.check = true;
                    if (!$scope.user.userName) {
                    	return;
                    }
                    if (!$scope.user.staffNum) {
                    	return;
                    }
                    $http({
                        method: "put",
                        data: angular.toJson($scope.user),//JsonData = {"id":1,"value":"hello"}
                        url: host+"/user/"+user.id,
                        headers: { "Content-Type": "application/json" }
                    }).success(function (d) { 
                    	if (d.statusCode=='OK') {
                            SweetAlert.swal("编辑成功", "", "success");
                            $state.reload()
                        } else {
                        	SweetAlert.swal("编辑失败", d.msg, "error");
                        }
                        $modalInstance.close();
                        $state.reload();
                    }).error(function(error){
                    	console.log(error);
                    });
                };
                $scope.cancel = function(){
                    $modalInstance.dismiss();
                }
            },
            size: 'md'
        });
    };
    
    // 删除
    $scope.del = function (id) {
        if (!id){
            SweetAlert.swal("提示", "请选择要删除的用户!", "warning");
            return;
        }
        SweetAlert.swal({
                title: "提示",
                text: "是否确定删除此用户?",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: false},
            function(ok){
                if (ok) {
                	$http({
                        method: "delete",
                        url: host+"/user/"+id,
                    }).success(function (d) { 
                    	console.dir(d);
                    	if (d.statusCode=='OK') {
                    		SweetAlert.swal("删除成功", "成功删除用户！", "success");
                            $state.reload()
                        } else {
                        	SweetAlert.swal("删除失败", d.message, "error");
                        }
                    }).error(function(error){
                    	console.log(error);
                    });
                }
            });
    };
    
    // 锁定/激活
    $scope.lock = function (id, status) {
        if (!id){
            SweetAlert.swal("提示", "没有选定目标用户！", "warning");
            return;
        }
        var alertText = '';
        if(status == 100) {
        	alertText = "是否锁定此用户?";
        } else if(status == 101) {
        	alertText = "是否激活此用户?"
        }
        SweetAlert.swal({
                title: "提示",
                text: alertText,
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                closeOnConfirm: false},
            function(ok){
                if (ok) {
                	$http({
                        method: "get",
                        url: host+"/user/"+id+"/lock/"+status,
                    }).success(function (d) { 
                    	if (d.statusCode=='OK') {
                    		SweetAlert.swal("操作成功", d.message, "success");
                            $state.reload()
                        } else {
                        	SweetAlert.swal("操作失败", d.message, "error");
                        }
                    }).error(function(error){
                    	console.log(error);
                    });
                }
            });
    };
});