<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

  <h1>Edit Info</h1>
  <form:form action="/edit/ok" commandName="signUpForm">
    <table>
      <tr>
        <td><form:label path="email">email</form:label></td>
        <td><form:input path="email" /></td>
      </tr>
      <tr>
        <td><form:label path="password">password</form:label></td>
        <td><form:password path="password"/> </td>
      </tr>
      <tr>
        <td><form:label path="login">Login</form:label></td>
        <td><%= request.getParameter("login")%> </td>
      </tr>
      <tr>
        <td><form:label path="fio">Full name</form:label></td>
        <td><form:input path="fio"/> </td>
      </tr>
      <tr>
        <td colspan="2" style="text-align:center;">
          <input type="submit" value="Ok"/>
        </td>
      </tr>
    </table>
  </form:form>

</div>
