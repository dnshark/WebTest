<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
    <form:form action="/tutor/addTest" commandName="testForm">
        <h1>New test</h1>
        <table align="center">
            <tr>
                <td>Test name</td>
                <td><form:input path="name"/> </td>
            </tr>
            <tr>
                <td>Time per question</td>
                <td><form:input path="timePerQuestion"/> </td>
            </tr>
            <tr>
                <td>
                    Description
                </td>
                <td><form:input path="description"/> </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Add new test" />
                </td>
            </tr>
        </table>
    </form:form>

</div>
