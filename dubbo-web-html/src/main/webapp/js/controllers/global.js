/**
 * Created by zml on 2017/2/20.
 */
app.controller('GlobalCtrl',function($scope,$state){	// 隐式注解
    $scope.reload = function () {
        $state.reload();
    };
    $scope.curState = function () {
        return $state.current.name;
    };
    $scope.renderTable = function () {
        $('.table').dataTable({
        	"lengthMenu": [2, 10, 25, 50, 75, 100]
        });
    };
    $scope.logout = function () {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("permissions");
    };
});