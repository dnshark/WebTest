<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
  <form action="testNew">
    <h1>New test</h1>
    <table align="center">
      <tr>
      <td>Test name</td>
      <td><input type="text"  name="testName" /> </td>
      </tr>
      <tr>
        <a href="/tutor/addTest">Add new test</a>
      </tr>
    </table>
  </form>

</div>
