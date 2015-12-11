<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7 "> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8 br-ie7"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9 br-ie8"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Web tester</title>
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/normalize.css?v=${CSS_JS_VERSION}" />
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/styles.css?v=${CSS_JS_VERSION}" />
</head>

<body class="style1">
	<header>
		<!--NEDIS не работает -->
			<sec:authorize access="hasAnyRole('ADMIN','TUTOR','ADVANCED_TUTOR','STUDENT')">
				<a href="/logout">logout</a>
			</sec:authorize>

	</header>
	<section class="main">
		<decorator:body />
	</section>
	<script src="${context}/resources/js/jquery-1.10.2.js?v=${CSS_JS_VERSION}"></script>
	<script src="${context}/resources/js/scripts.js"></script>

	<decorator:getProperty property="page.customscripts" />
</body>
</html>