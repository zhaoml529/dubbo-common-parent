/**
 * angular permission module 
 *
 * No dependence
 */

angular.module('angular.permission', ['ngStorage'])

/*  .config(['$httpProvider', function($httpProvider) {
      $httpProvider.responseInterceptors.push('httpResponsePermissionInterceptor');
    }
  ])*/

  /**
   * if route change then check if user has permission
   */
/*  .run(['$rootScope', function($rootScope){
	  $rootScope.permissions="";
  }])*/

  /**
   * factory service provide permission data set and check
   */
  .factory('angularPermission', ['$rootScope',function ($rootScope) {
    return {
      setPermissions: function(permissions) {
    	sessionStorage.permissions = permissions;	// 将权限列表放入SessionStorage中
        console.log("setPermission:"+sessionStorage.getItem('permissions'));
        $rootScope.$broadcast('permissionsChanged')
      },
      hasPermission: function (permission) {
    	console.log("permissions:"+sessionStorage.getItem('permissions'));
        if(sessionStorage.getItem('permissions').indexOf(permission.trim()) > -1){
          return true;
        }else{
          return false;
        }
      }
   };
  }])

  /**
   * directive to show or hide UI by permission
   */
  .directive('hasPermission',['angularPermission',function(angularPermission) {
    return {
      link: function(scope, element, attrs) {
        if(!angular.isString(attrs.hasPermission))
          throw "hasPermission value must be a string!";
        var value = attrs.hasPermission.trim();
        var notPermissionFlag = value[0] === '!';
        if(notPermissionFlag) {
          value = value.slice(1).trim();
        }

        function toggleVisibilityBasedOnPermission() {
          var hasPermission = angularPermission.hasPermission(value);
          if(hasPermission && !notPermissionFlag || !hasPermission && notPermissionFlag){
            element.show();
          }else{
            element.remove();
          }
        }
        toggleVisibilityBasedOnPermission();
        scope.$on('permissionsChanged', toggleVisibilityBasedOnPermission);
      }
    };
  }])

  /**
   * http interceptor that if user has no permission,redirect to other page
   */
  /*.factory('httpResponsePermissionInterceptor',['$q','$location',function ($q,$location) {
    return function (promise) {
      return promise.then(function (response) {
          // http response Normal,you can alse to something here like notify
          return response;
        }, function (response) {
          if(response.status === 403 || response.status === 401) {
            // http response status code 403 or 401 that means use has no permission
            // here I redirect page to '/unauthorized',you can alse do anything you want
            $location.path('/unauthorized');
            return $q.reject(response);
          }
          return $q.reject(response);
      });
    };
  }]);*/