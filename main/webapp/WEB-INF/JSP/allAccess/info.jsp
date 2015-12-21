<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container">
    <div id="maincontent_container">
        <div id="maincontent">
            <h2>User Info</h2>

            <div class="form_field">
                <strong>Email</strong>
                <strong>${account.email}</strong>
            </div>
            <div class="form_field">
                <strong>Login</strong>
                <strong>${account.login}</strong>
            </div>

            <div class="form_field">
                <strong>Full name</strong>
                <strong>${account.fio}</strong>
            </div>

            <div class="form_field">
                <strong>Created</strong>
                <strong>${account.created}</strong>
            </div>
        </div>
    </div>
</div>


