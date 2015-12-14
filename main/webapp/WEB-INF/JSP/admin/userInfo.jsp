<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">
  <h2>User Info</h2>
  <form:form method="post" commandName="adminForm">
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
          <form:checkboxes path="checkRoles" items="${adminForm.allRoles}" itemLabel="name" itemValue="id" delimiter="<br/>" />
        </td>
      </tr>
      <c:if test="${mode == 'edit'}">
        <tr>
          <td colspan="2" style="text-align:center;">
            <input type="submit" value="Save" onclick="form.action='/admin/update/id${adminId}';">
          </td>
          <td colspan="2" style="text-align:center;">
            <input type="submit" value="Delete" onclick="form.action='/admin/delete/id${adminId}';">
          </td>
        </tr>
      </c:if>
      <c:if test="${mode == 'new'}">
        <tr>
          <td colspan="4" style="text-align:center;">
            <input type="submit" value="Add" onclick="form.action='/admin/add';">
          </td>
        </tr>
      </c:if>

    </table>
  </form:form>

</div>