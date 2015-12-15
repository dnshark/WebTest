
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="container">
	<h1>Hello admin</h1>
	<table align="center">
		<tr>
			<td>
				<a href="list/users?page=0&count=50">Users list</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="user/info/new">Add new user</a>
			</td>
		</tr>
	</table>
</div>