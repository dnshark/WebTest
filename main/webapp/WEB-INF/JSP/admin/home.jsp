<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container">
    <div id="maincontent_container">
        <div id="maincontent">
            <h1>Hello admin</h1>

            <div class="main_box">
                <li>
                    <sp><a href="list/users">Users list</a></sp>
                </li>

                <li>
                    <sp><a href="user/info/new">Add new user</a></sp>
                </li>
            </div>
        </div>
    </div>
</div>