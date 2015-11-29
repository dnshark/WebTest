<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<h2>Please login</h2>
	<form:form method="POST" action="${context }/login" commandName="loginForm" >
	   <table>
	    <tr>
	        <td colspan="2" class="errors"><form:errors path="*"/></td>
			<td><a href="/signup">New user</a></td>
	    </tr>
	    <tr>
	        <td><form:label path="login">Login</form:label></td>
	        <td><form:input path="login" /></td>
	    </tr>
	    <tr>
	        <td><form:label path="password">Password</form:label></td>
	        <td><form:password path="password"/> </td>
	    </tr>
	    <tr>
	        <td><form:label path="idRole">Role</form:label></td>
	        <td>
	        	<form:select path="idRole">
	        		<form:option value="0" label="--- Select ---"/>
   					<form:options items="${roles}" itemLabel="name" itemValue="idRole" />
	        	</form:select> 
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2" style="text-align:center;">
	            <input type="submit" value="Login"/>
	        </td>
	    </tr>
	    <tr>
			<td colspan="2" style="text-align:center;padding-top:20px;">
				<a href="${context }/fbLogin">
					<img alt="fbLogin" src="${context }/resources/images/login-facebook.png"/>
				</a>
			</td>
		</tr>
	</table>  
	</form:form>
</div>