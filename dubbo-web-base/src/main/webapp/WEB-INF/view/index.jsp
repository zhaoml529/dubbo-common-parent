<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh" data-ng-app="app">
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="renderer" content="webkit">
  <title></title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${ctx}/dist/css/vendor.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/dist/css/bundle.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/css/vendor/ng-pagination.css" type="text/css" />
  <script type="text/javascript" src="${ctx}/dist/js/vendor.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/app.js"></script>
  <script type="text/javascript" src="${ctx}/js/config.js"></script>
  <script type="text/javascript" src="${ctx}/js/config.router.js"></script>
  <script type="text/javascript" src="${ctx}/js/common.js"></script>
  <script type="text/javascript" src="${ctx}/js/vendor/angular/ng-pagination.js"></script>
  <script type="text/javascript" src="${ctx}/js/controllers/signin.js"></script>
  <script type="text/javascript" src="${ctx}/js/controllers/app.js"></script>
  <script type="text/javascript" src="${ctx}/js/controllers/global.js"></script>
</head>
<body data-ng-controller="AppCtrl">
	<!-- <div ng-include="'navbar'"></div> -->
  	<div data-ui-view></div>
</body>
</html>