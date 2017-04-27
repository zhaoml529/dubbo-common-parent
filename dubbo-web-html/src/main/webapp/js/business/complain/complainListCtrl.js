/**
 * Created by yyx on 2017/3/2.
 * ”√ªßøÿ÷∆≤„
 */
app.controller('complainListCtrl',function ($scope,$modal,$state,SweetAlert,complainListService,comService,$stateParams) {
    $scope.pageChanged = function() {
        // var param = {"currPage" : $scope.paginationConf.currentPage, "numPage" : $scope.paginationConf.itemsPerPage, "paramMap" : {"userName" : "admin", "staffNum" : "10001"}};
        var param = {"currPage" : $scope.paginationConf.currentPage, "numPage" : $scope.paginationConf.itemsPerPage};
        var address="/listUser";
        comService.listPage(param,address).then(
            function(data){
                if (data.statusCode==200) {
                    console.log(data.totalCount + "-" + data.data);
                    $scope.paginationConf.totalItems = data.totalCount;
                    $scope.paginationConf.totalPages=Math.ceil(data.totalCount/$scope.paginationConf.itemsPerPage);
                    $scope.userList = data.data;
                } else {
                    $('.table').dataTable();
                }
            }
        );
    };
});