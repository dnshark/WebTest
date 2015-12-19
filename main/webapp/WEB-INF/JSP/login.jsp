<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="container">
	<div id="login_container">
		<form method="POST" action="${context }/loginHandler" >
			<div id="userlogin">
				<h2>Please login</h2>
				<div class="form_field"></div>
				<div class="clearthis"></div>
				<div class="form_field">
					<strong> Login</strong>
					<input type="text"  name="j_username" />
				</div>
				<div class="clearthis"></div>
				<div class="form_field">
					<strong> Password</strong>
					<input type="password" name="j_password"/>
				</div>
				<div id="link-password">
					<a href="/forget">Forget password</a>
				</div>
				<div class="clearthis"></div>
				<div class="form_remember_me">
					<input type="checkbox" value="true" name="_spring_security_remember_me" >Remember me
				</div>
				<div class="clearthis"></div>
				<div class="form_field">
					<strong>Role</strong>
						<select name="idRole">
							<option value="0">--- Select ---</option>
							<c:forEach var="role" items="${roles }">
								<option value="${role.idRole }">${role.name }</option>
							</c:forEach>
						</select>
				</div>
				<div class="clearthis"></div>

				<div class="clearthis"></div>
				<div class="form_button">
					<input type="submit" value="Login"/>
				</div>
				<div class="clearthis"></div>
				<div class="form_exception">
					<c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null }">
						<td colspan="2" class="errors">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</td>
					</c:if>
				</div>
				<div class="clearthis"></div>
				<div class="form_facebook">
					<a href="${context }/fbLogin">
						<img alt="fbLogin" src="${context }/resources/images/login-facebook.png" />
					</a>
				</div>
				<div class="clearthis"></div>
			</div>
		</form>

	</div>
</div>