/**
 * Created by zml on 2017/3/2.
 */
app.controller('userCtrl',function ($scope,$modal,$http,host,$state) {
    $http.get(host+'/user').success(function (d) {
        if (d.statusCode=='OK')
            $scope.userList = d.data;
        if (d.data.length==0) $('.table').dataTable();
    });
});