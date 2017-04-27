/**
 * Created by yyx on 2017/3/2.
 * 用户控制层
 */
app.controller('goodsCtrl',function ($scope,$modal,$state,SweetAlert,goodsService,comService,$stateParams) {
    $scope.pageChanged = function() {
        // var param = {"currPage" : $scope.paginationConf.currentPage, "numPage" : $scope.paginationConf.itemsPerPage, "paramMap" : {"userName" : "admin", "staffNum" : "10001"}};
        var param = {"currPage" : $scope.paginationConf.currentPage, "numPage" : $scope.paginationConf.itemsPerPage};
        var address="/goods/list";
        comService.listPage(param,address).then(
            function(data){
                if (data.statusCode==200) {
                    console.log(data.totalCount + "-" + data.data);
                    $scope.paginationConf.totalItems = data.totalCount;
                    $scope.paginationConf.totalPages=Math.ceil(data.totalCount/$scope.paginationConf.itemsPerPage);
                    $scope.goodsList = data.data;
                } else {
                    $('.table').dataTable();
                }
            }
        );
    };
    // 编辑
    $scope.toGoodsDetail = function (id) {
        $state.go('index.goodsDetail', {id: id});
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
                    comService.delUser(id).then(
                		function(data){
                			if (data.statusCode==200) {
                        		SweetAlert.swal("删除成功", "成功删除用户！", "success");
                                $state.reload();
                            } else {
                            	SweetAlert.swal("删除失败", data.message, "error");
                            }
                	    }	
                    );
                }
        });
    };
});