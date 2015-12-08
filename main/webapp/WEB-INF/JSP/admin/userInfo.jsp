<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
  <h2>User Info</h2>
  <form:form action="/admin/Ok${adminId}" method="post" commandName="adminForm">
    <table align="center">
      <tr>
        <td>Email</td>
        <td><form:input path="email" /></td>
        <td>Confirmed</td>
        <td><form:checkbox path="confirmed"/> </td>
      </tr>
      <tr>
        <td>Login</td>
        <td><form:input path="login"/> </td>
        <td>Active</td>
        <td><form:checkbox path="active"/> </td>
      </tr>

      <tr>
        <td>Password</td>
        <td><form:password path="password"/> </td>
      </tr>

      <tr>
        <td>Full name</td>
        <td><form:input path="fio"/> </td>
      </tr>
      <tr>
        <td>Role</td>
        <td>
          <form:checkboxes path="checkRoles" items="${allRoles}" itemLabel="name" itemValue="name" delimiter="<br/>" />
        </td>
      </tr>
      <c:if test="${mode == 'edit'}">
        <tr>
          <td colspan="2" style="text-align:center;">
            <input type="submit" name="button" value="save"/>
          </td>
          <td colspan="2" style="text-align:center;">
            <input type="submit" name="button" value="delete"/>
          </td>
        </tr>
      </c:if>
      <c:if test="${mode == 'new'}">
        <tr>
          <td colspan="4" style="text-align:center;">
            <form:button type="submit" name="button" value="add"/>
          </td>
        </tr>
      </c:if>

    </table>
  </form:form>

</div>