<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <form:form action="/forget" commandName="signUpForm">
    <div id="maincontent_container">
        <div id="maincontent">
            <h2>Please SignUp</h2>

            <div class="form_field">
                <strong>Email</strong>
                <form:input path="email"/>
            </div>
            <div class="clearthis"></div>
            <div id="submit_button">
                <input type="submit" value="Send"/>
            </div>
        </div>
        </form:form>
    </div>
