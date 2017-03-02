/**
 * Created by zml on 2017/3/2.
 */
app.controller('userCtrl',function ($scope,$modal,$http,host,$state) {
    $http.get(host+'/user').success(function (d) {
        if (d.statusCode=='OK')
            $scope.userList = d.data;
        if (d.data.length==0) $('.table').dataTable();
    });
    
    // 编辑
    $scope.edit = function (id) {
    	console.dir($scope.userList[id]);
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
                    
                    $http.put(host+'/user/'+user.id, $scope.user).success(function (d) {
                        if (d.statusCode=='OK') {
                            SweetAlert.swal("编辑成功", "", "success");
                            $state.reload()
                        } else {
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
    }
});