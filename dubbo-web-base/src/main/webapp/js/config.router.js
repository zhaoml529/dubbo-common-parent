'use strict';

/**
 * Config for the router
 */
angular.module('app')
    .run(
        [          '$rootScope', '$state', '$stateParams',
            function ($rootScope,   $state,   $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    )
    .config(
        [          '$stateProvider', '$urlRouterProvider',
            function ($stateProvider,   $urlRouterProvider) {

                $urlRouterProvider.otherwise('/index');
                
                $stateProvider
                    .state('signin', {
                        controller: 'SigninCtrl',
                        url: '/signin',
                        templateUrl: 'tpls/signin.html',
                        resolve: {
		                    deps:["$ocLazyLoad",function($ocLazyLoad){
		                        return $ocLazyLoad.load("js/controllers/signin.js");
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
			                    templateUrl: "tpls/user/user-list.html"
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
                    .state('app.admin', {
                        url: '/admin',
                        templateUrl: 'tpls/admin/admin.html'
                    })
                    .state('app.teacher', {
                        url: '/teacher',
                        templateUrl: 'tpls/teacher/teacher.html'
                    })
                    .state('app.student', {
                        url: '/student',
                        templateUrl: 'tpls/student/student.html'
                    })
                    .state('app.teacher.allCourse', {
                        controller: 'TeacherAllCourseCtrl',
                        url: '/allCourse',
                        templateUrl: 'tpls/teacher/all-course.html'
                    })
                    .state('app.teacher.myCourse', {
                        controller: 'TeacherMyCourseCtrl',
                        url: '/myCourse',
                        templateUrl: 'tpls/teacher/my-course.html'
                    })
                    .state('app.student.allCourse', {
                        controller: 'StudentAllCourseCtrl',
                        url: '/allCourse',
                        templateUrl: 'tpls/student/all-course.html'
                    })
                    .state('app.student.myCourse', {
                        controller: 'StudentMyCourseCtrl',
                        url: '/myCourse',
                        templateUrl: 'tpls/student/my-course.html'
                    })
                    .state('app.admin.dept', {
                        controller: 'DeptCtrl',
                        url: '/dept',
                        templateUrl: 'tpls/admin/dept.html'
                    })
                    .state('app.admin.class', {
                        controller: 'ClassCtrl',
                        url: '/class',
                        templateUrl: 'tpls/admin/class.html'
                    })
                    .state('app.admin.teacher', {
                        controller: 'TeacherManageCtrl',
                        url: '/teacher',
                        templateUrl: 'tpls/admin/teacher.html'
                    })
                    .state('app.admin.student', {
                        controller: 'StudentManageCtrl',
                        url: '/student',
                        templateUrl: 'tpls/admin/student.html'
                    })
                    .state('app.admin.course', {
                        controller: 'CourseManageCtrl',
                        url: '/course',
                        templateUrl: 'tpls/admin/course.html'
                    })
                    .state('app.admin.password', {
                        controller: 'ChangePwdCtrl',
                        url: '/password',
                        templateUrl: 'tpls/common/change-pwd.html'
                    })
                    .state('app.teacher.password', {
                        controller: 'ChangePwdCtrl',
                        url: '/password',
                        templateUrl: 'tpls/common/change-pwd.html'
                    })
                    .state('app.student.password', {
                        controller: 'ChangePwdCtrl',
                        url: '/password',
                        templateUrl: 'tpls/common/change-pwd.html'
                    })
                    .state('app.teacher.grade', {
                        controller: 'RecordGradeCtrl',
                        url: '/recordGrade/:cid',
                        templateUrl: 'tpls/teacher/grade.html'
                    })
                    .state('app.student.grade', {
                        controller: 'QueryGradeCtrl',
                        url: '/queryGrade/:cid',
                        templateUrl: 'tpls/student/grade.html'
                    })
            }
        ]
    );