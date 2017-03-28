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
    
    /**
     * app.js 初始化
     * $scope.paginationConf.maxSize		页面上可选页数范围
     * $scope.paginationConf.totalItems		总共有多少数据
     * $scope.paginationConf.itemsPerPage	每页多少条数据
     * $scope.paginationConf.currentPage	当前页
     * 每个Controller层都会先在当前的scope找需要的变量，找不到，再到rootscope上去寻找。如果还是找不到，就会报错。
     * -------------------------------------
     * 请求传参
     * currPage				当前页
     * numPage				每页多少条数据
     * paramMap				封装的查询条件
     * -------------------------------------
     * 请求响应数据 d
     * title				提示标题
     * message				提示信息
     * statusCode			http状态码 200-OK 请求成功
     * 								 400-Bad Request
     * 								 401-Unauthorized 认证失败
     * 								 403-Forbidden 无权限
     * 								 405-Method Not Allowed
     * 								 415-Unsupported Media Type
     * 								 500-Internal Server Error 服务器错误
     * data					请求成功时，需要传递的数据。
     * totalCount			分页总记录数
     * fieldErrors			字段验证错误列表 entryName:验证的实体名称、fieldName:验证的字段名称、errorMessage:验证的错误信息
     * 
     */
    $scope.pageChanged = function() {
        // var param = {"currPage" : $scope.paginationConf.currentPage, "numPage" : $scope.paginationConf.itemsPerPage, "paramMap" : {"userName" : "admin", "staffNum" : "10001"}};
        var param = {"currPage" : $scope.paginationConf.currentPage, "numPage" : $scope.paginationConf.itemsPerPage};
        $http({
            method: "post",
            data: angular.toJson(param),//JsonData = {"id":1,"value":"hello"}
            url: host+"/listUser"
        }).success(function (d) { 
        	if (d.statusCode==200) {
        		console.log(d.totalCount + "-" + d.data);
        		$scope.totalItems = d.totalCount;
            	$scope.userList = d.data;
            } else {
            	$('.table').dataTable();
            }
        });
        /*.error(function(error){
        	console.log(error);
        });*/
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
                    console.log(angular.toJson($scope.user));
                    $http({
                        method: "post",
                        data: angular.toJson($scope.user),//JsonData = {"id":1,"value":"hello"}
                        url: host+"/user",
                        headers: { "Content-Type": "application/json" }
                    }).success(function (d) { 
                    	if (d.statusCode==200) {
                    		SweetAlert.swal("增加成功", "新增用户“"+$scope.user.userName+"”", "success");
                        } else {
                        	SweetAlert.swal("增加失败", d.message, "error");
                        }
                        $modalInstance.close();
                        $state.reload();
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
                    /*if (!$scope.user.userName) {
                    	return;
                    }*/
                    if (!$scope.user.staffNum) {
                    	return;
                    }
                    $http({
                        method: "put",
                        data: angular.toJson($scope.user),//JsonData = {"id":1,"value":"hello"}
                        url: host+"/user/"+user.id,
                        headers: { "Content-Type": "application/json" }
                    }).success(function (d) { 
                    	if (d.statusCode==200) {
                            SweetAlert.swal("编辑成功", "", "success");
                            $state.reload()
                        } else {
                        	console.log(d.fieldErrors);
                        	SweetAlert.swal("编辑失败", d.msg, "error");
                        }
                        $modalInstance.close();
                        $state.reload();
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
                    	if (d.statusCode==200) {
                    		SweetAlert.swal("删除成功", "成功删除用户！", "success");
                            $state.reload()
                        } else {
                        	SweetAlert.swal("删除失败", d.message, "error");
                        }
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
                    	if (d.statusCode==200) {
                    		SweetAlert.swal("操作成功", d.message, "success");
                            $state.reload()
                        } else {
                        	SweetAlert.swal("操作失败", d.message, "error");
                        }
                    });
                }
            });
    };
});