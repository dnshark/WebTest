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
	<!--<link rel="stylesheet" type="text/css" href="${context}/resources/css/normalize.css?v=${CSS_JS_VERSION}" /> -->
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/style.css?v=${CSS_JS_VERSION}"  media="screen"/>
	</head>

<body>
<div id="container">
		<!-- Start of Page Header -->
		<div id="header_container">
			<div id="page_header">
				<div id="header_company">
					<h1><span>Fibreglass Company</span></h1>
				</div>
				<div id="header_menu">
					<ul>
						<sec:authorize access="isAuthenticated()">
						<li><a href="/myInfo"><span>Home</span></a></li>
						<li><a href="/edit/info"><span>Edit Info</span></a></li>
						<li><a href="/result"><span>View results</span></a></li>
						<li><a href="/info"><span>Look Info</span></a></li>
						<li><a href="/logout"><span>Logout</span></a></li>
						</sec:authorize>
						<sec:authorize access="!isAuthenticated()">
							<li><a href="/signup"><span>Sign up</span></a></li>
						</sec:authorize>
					</ul>
				</div>
				<div id="header_welcome">
					<h3>Welcome</h3>
					<div id="welcome_text">
						<p>Site for testing</p>
						<p>This is a site designed by Krupa Dmitrij.</p>
					</div>
				</div>
			</div>
		</div>
		<!-- End of Page Header -->
	<section class="main">
		<decorator:body />
	</section>
	<!-- Start of Page Footer -->
	<div id="page_footer"> <p>All questions you can send to e-mail: dnshark@mail.ru</p>
	</div>
	<!-- End of Page Footer -->


	<script src="${context}/resources/js/jquery-1.10.2.js?v=${CSS_JS_VERSION}"></script>
	<script src="${context}/resources/js/scripts.js"></script>

	<decorator:getProperty property="page.customscripts" />
  </div>
</body>
</html>