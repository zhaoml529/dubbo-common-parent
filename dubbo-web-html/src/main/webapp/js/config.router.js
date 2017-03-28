'use strict';

/**
 * Config for the router
 */
angular.module('app')
    .run(
        [  '$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
        		/*通过rootscope设置全局变量
        		 *AngularJS is a JavaScript framework, 
        		 *everything is stored in memory heap and the heap is starts when you open a page and it's destroyed after you close it. 
        		 *In this context, browser refresh is like closing and re-opening the page.
        		 **/
        		$rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
                
                $rootScope.paginationConf = {  
            		maxSize : 5,		// 页面上可选页数范围
                    currentPage : 1, 	// 当前页
                    itemsPerPage : 2, 	// 每页显示多少条数据   
                };  
            }
        ]
    )
    .config(
        [   '$stateProvider', '$urlRouterProvider',
            function ($stateProvider,   $urlRouterProvider) {
                $urlRouterProvider.otherwise('/index');
                
                $stateProvider
                    .state('login', {
                        controller: 'SigninCtrl',
                        url: '/login',
                        templateUrl: 'tpls/login.html',
                        resolve: {
		                    deps:["$ocLazyLoad",function($ocLazyLoad){
		                        return $ocLazyLoad.load("js/controllers/login.js");
		                    }]
                        }
                    })
                    .state("index",{
			            url: "/index",
			            views : {
			                '' : {
			                    templateUrl: "tpls/layout/app.html"
			                },
			                'header@index' :{
			                    templateUrl: "tpls/layout/header.html"
			                },
			                'menu@index' :{
			                    templateUrl: "tpls/layout/menu.html"
			                },
			                'main@index' :{
			                	templateUrl: "tpls/layout/main.html"
			                },
			                'footer@index' :{
			                    templateUrl: "tpls/layout/footer.html"
			                }
			            }
			        })
			        .state('index.userList', {
                        url: '/user',
                        views : {
                        	'main' :{
                        		controller: 'userCtrl',
			                    templateUrl: "tpls/user/user-list.html",
		                        permission: 'view:list'
			                }
                        },
                        resolve: {
		                    users:["$ocLazyLoad",function($ocLazyLoad){
		                        return $ocLazyLoad.load("js/controllers/user.js");
		                    }]
                        }
                    })
                    .state('app', {
                        controller: 'GlobalCtrl',
                        url: '/app',
                        templateUrl: 'tpls/app.html'
                    })
            }
        ]
    );