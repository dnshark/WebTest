<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <form:form method="post" commandName="adminForm">
        <div id="maincontent_container">
            <div id="maincontent">
                <div id="started_container">
                    <h2>User Info</h2>

                    <div class="form_field">
                        <strong>Email</strong>
                        <form:input path="email"/>
                    </div>
                    <div class="form_field">
                        <strong>Login</strong>
                        <form:input path="login"/>
                    </div>
                    <div class="form_field">
                        <strong>Password</strong>
                        <form:password path="password"/>
                    </div>
                    <div class="form_field">
                        <strong>Full name</strong>
                        <form:input path="fio"/>
                    </div>
                    <div class="form_field">
                        <strong>Active</strong>
                        <form:checkbox path="active"/>
                    </div>
                    <div class="form_field">
                        <strong>Confirmed</strong>
                        <form:checkbox path="confirmed"/>
                    </div>
                    <c:if test="${mode == 'edit'}">
                        <div id="submit_button">
                            <input type="submit" value="Save" onclick="form.action='/admin/update/user/id${adminId}';">
                        </div>
                        <div id="submit_button">
                            <input type="submit" value="Delete"
                                   onclick="form.action='/admin/delete/user/id${adminId}';">
                        </div>
                    </c:if>
                    <c:if test="${mode == 'new'}">
                        <div id="submit_button">
                            <input type="submit" value="Add" onclick="form.action='/admin/add/user';">
                        </div>
                    </c:if>
                </div>
                <div id="right_container">
                    <div id="repairing">
                        <h2><span>Roles</span></h2>

                        <div id="form_roles">
                            <strong>
                                <form:checkboxes path="checkRoles" items="${adminForm.allRoles}" itemLabel="name"
                                                 itemValue="id" delimiter="<strong>"/>
                            </strong>
                        </div>
                    </div>
                    <div class="clearthis">&nbsp;</div>

                </div>
            </div>

        </div>
    </form:form>
</div>