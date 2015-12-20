<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
  <div id="maincontent_container">
    <div id="maincontent">

      <form:form method="POST" action="next" commandName="testPassForm" >
        <span id="trv"></span><script>TRT ()</script>

        <table width="100%">
          <tr><td width="100%" align="center"><span id="theTime" class="timeClass"></span></td></tr>
        </table>

        <h1>${testPassForm.question.name}</h1>
        <div class="main_box">
          <table align="center">
            <c:forEach var="answer" items="${testPassForm.answers}">
              <tr>
                <td>
                  <input type="checkbox" name="answer" id="${answer.id}" value="${answer.id}"><strong> ${answer.name}</strong>
                </td>
              </tr>
            </c:forEach>
            <tr>
              <td>
                <input type="checkbox" name="answer" id="-1" value="-1"> <strong>Don't know</strong>
              </td>
            </tr>
            <tr>
              <td>
                <div id="submit_button">
                  <input type="submit" name="Ok" value="Ok"/>
                </div>
              </td>
            </tr>
          </table>
        </div>


      </form:form>

    </div>
  </div>
</div>

<content tag="customscripts">
  <script>
    $(document).ready(function(){
      initTime(${testPassForm.timePerQuestion},0);
    });
  </script>
</content>