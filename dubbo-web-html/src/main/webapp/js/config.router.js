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
				itemsPerPage : 10, 	// 每页显示多少条数据
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
							return $ocLazyLoad.load("js/common/login.js");
						}]
					}
				})
				.state("index",{  //所有页面的入口
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
							controller: 'userCtrl',
							templateUrl: "tpls/user/user-list.html",   //页面进入后默认进入页
							resolve: {
								users:["$ocLazyLoad",function($ocLazyLoad){
									return $ocLazyLoad.load(
										[
											'js/business/user/userCtrl.js',
											'js/business/user/userService.js'
										]
									);
								}]
							}
						},
						'footer@index' :{
							templateUrl: "tpls/layout/footer.html"
						}
					},
					resolve: {
						global:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load([
								"js/common/global.js",
								"js/common/comService.js"
							]);
						}]
					}
				})
				.state('index.userList', {
					url: '/user',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'userCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/user/user-list.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/user/userCtrl.js',
									'js/business/user/userService.js'
								]
							);
						}]
					}
				})
				//订单管理--商品查询
				.state('index.goodsList', {
					url: '/goods',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'goodsCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/goods/goods-list.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/goods/goodsCtrl.js',
									'js/business/goods/goodsService.js'
								]
							);
						}]
					}
				})
				//订单管理--商品修改
				.state('index.goodsDetail', {
					url: '/goods/detail/:id',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'goodsDetailCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/goods/goods-detail.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/goods/goodsDetailCtrl.js',
									'js/business/goods/goodsDetailService.js'
								]
							);
						}]
					}
				})
				//订单管理--商品录入
				.state('index.goodsInput', {
					url: '/goods',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'goodsInputCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/goods/goods-input.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/goods/goodsInputCtrl.js',
									'js/business/goods/goodsInputService.js'
								]
							);
						}]
					}
				})
				//订单管理--已送达商品汇总
				.state('index.goodsSum', {
					url: '/goods',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'goodsSumCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/goods/goods-sum.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/goods/goodsSumCtrl.js',
									'js/business/goods/goodsSumService.js'
								]
							);
						}]
					}
				})
				//订单管理--金额统计
				.state('index.goodsAmount', {
					url: '/goods',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'goodsSumCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/goods/goods-amount.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/goods/goodsAmountCtrl.js',
									'js/business/goods/goodsAmountService.js'
								]
							);
						}]
					}
				})
				//送货员--投诉列表
				.state('index.complainList', {
					url: '/complain',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							//controller: 'complainListCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/complain/complain_list.html",//页面路径
							permission: 'view:list'//定义视频
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/complain/complainListCtrl.js',
									'js/business/complain/complainListService.js'
								]
							);
						}]
					}
				})
				//送货员--投诉录入
				.state('index.complainAdd', {
					url: '/complain',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'complainAddCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/complain/complain_Add.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/complain/complainAddCtrl.js',
									'js/business/complain/complainAddService.js'
								]
							);
						}]
					}
				})
                //送货员--信息管理--账号管理(列表页)
                .state('index.AccountqueList', {
                    url: '/admin',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/Account/Account_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    },
                    resolve: {
                        users:["$ocLazyLoad",function($ocLazyLoad){
                            return $ocLazyLoad.load( //页面引用的js
                                [
                                    'js/business/user/userCtrl.js',
                                    'js/business/user/userService.js'
                                ]
                            );
                        }]
                    }
                })
                //送货员--信息管理--账号管理(添加页)
                .state('index.AccountqueList.AccountaddList', {
                    url: '/admin_add',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/Account/Account_add.html"//页面路径
                            // permission: 'view:list'
                        }
                    },
                    resolve: {
                        users:["$ocLazyLoad",function($ocLazyLoad){
                            return $ocLazyLoad.load( //页面引用的js
                                [
                                    'js/business/user/userCtrl.js',
                                    'js/business/user/userService.js'
                                ]
                            );
                        }]
                    }
                })
                //送货员--信息管理--账号管理(添加按钮)
                .state('index.AccountqueList.AccountaddList_add', {
                    url: '/admin_adds',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/Account/Account_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    },
                    resolve: {
                        users:["$ocLazyLoad",function($ocLazyLoad){
                            return $ocLazyLoad.load( //页面引用的js
                                [
                                    'js/business/user/userCtrl.js',
                                    'js/business/user/userService.js'
                                ]
                            );
                        }]
                    }
                })
                //送货员--信息管理--角色管理(列表页)
                .state('index.rolequeList', {
                    url: '/role',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/role/role_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--角色管理(添加页)
                .state('index.rolequeList.roleaddList', {
                    url: '/role_add',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/role/role_add.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--角色管理(添加按钮)
                .state('index.rolequeList.roleaddList_add', {
                    url: '/role_adds',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/role/role_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })



                //送货员--信息管理--岗位管理(列表页)
                .state('index.postqueList', {
                    url: '/post',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/post/post_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--岗位管理(添加页)
                .state('index.postqueList.postaddList', {
                    url: '/post_add',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/post/post_add.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--岗位管理(添加按钮)
                .state('index.postqueList.postaddList_add', {
                    url: '/post_adds',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/post/post_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })


                //送货员--信息管理--部门管理(列表页)
                .state('index.departmentqueList', {
                    url: '/department',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/department/department_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--部门管理(添加页)
                .state('index.departmentqueList.departmentaddList', {
                    url: '/department_add',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/department/department_add.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--部门管理(添加按钮)
                .state('index.departmentqueList.departmentaddList_add', {
                    url: '/department_adds',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/department/department_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })


                //送货员--信息管理--公司管理(列表页)
                .state('index.companyqueList', {
                    url: '/company',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/company/company_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--公司管理(添加页)
                .state('index.companyqueList.companyaddList', {
                    url: '/company_add',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/company/company_add.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })
                //送货员--信息管理--公司管理(添加按钮)
                .state('index.companyqueList.companyaddList_add', {
                    url: '/company_adds',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
                    views : {
                        'main@index' :{
                            // controller: 'complainListCtrl',//控制器名称（userService.js）
                            templateUrl: "tpls/admin/company/company_list.html"//页面路径
                            // permission: 'view:list'
                        }
                    }
                })

				//片区管理--片区划分
				.state('index.areaList', {
					url: '/area',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'areaCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/area/area_list.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/area/areaCtrl.js',
									'js/business/area/areaService.js'
								]
							);
						}]
					}
				})

				//员工关怀--动态查询
				.state('index.staffList', {
					url: '/staff',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'staffCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/staff/staff_list.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/staff/staffCtrl.js',
									'js/business/staff/staffService.js'
								]
							);
						}]
					}
				})
				.state('index.staffAdd', {
					//url: '/staff/add/:id',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					url: '/staff',//地址栏中显示的名称http://localhost:8061/index.html#/index/user
					views : {
						'main' :{
							controller: 'staffAddCtrl',//控制器名称（userService.js）
							templateUrl: "tpls/staff/staff_add.html",//页面路径
							permission: 'view:list'
						}
					},
					resolve: {
						users:["$ocLazyLoad",function($ocLazyLoad){
							return $ocLazyLoad.load( //页面引用的js
								[
									'js/business/staff/staffAddCtrl.js',
									'js/business/staff/staffAddService.js'
								]
							);
						}]
					}
				})




			/*.state('app', {
			 controller: 'GlobalCtrl',
			 url: '/app',
			 templateUrl: 'tpls/app.html',
			 resolve: {
			 global:["$ocLazyLoad",function($ocLazyLoad){
			 return $ocLazyLoad.load("js/common/global.js");
			 }]
			 }
			 })*/
		}
	]
);
