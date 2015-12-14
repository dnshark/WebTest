<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
  <h1> Users list</h1>

  <table align="center">
    <c:forEach var="user" items="${users}">
      <tr>
        <td>
          <a href="/admin/user/id${user.id}">${user.fio}</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</div>
