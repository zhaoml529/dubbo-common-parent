/**
 * Created by zml on 2017/2/20.
 */
app.controller('GlobalCtrl',function($scope,$state,SweetAlert){	// 隐式注解
    $scope.reload = function () {
        $state.reload();
    };
    $scope.curState = function () {
        return $state.current.name;
    };
    $scope.renderTable = function () {
        $('.table').dataTable({
        	/*"lengthMenu": [2, 10, 25, 50, 75, 100]*/
        });
    };
    $scope.logout = function () {
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("permissions");
        SweetAlert.swal("提示", "注销成功！", "success");
        $state.go('login');
    };
    $scope.toggleMenu2 = function($event) {
       /* $scope.menu2 = !$scope.menu2;*/
        if($($event.target).next().length>0){
            $($event.target).next().slideToggle(150);
        }else{
            $($event.target).parent().next().slideToggle(150);
        }

    };
    $scope.toggleMenu3 = function($event) {
       /* $scope.menu3 = !$scope.menu3;*/
        if($($event.target).next().is(':visible')){
            $($event.target).removeClass('inactives');
        }else{
            $($event.target).addClass('inactives');
        }
        $($event.target).next().slideToggle(150);
    }
});