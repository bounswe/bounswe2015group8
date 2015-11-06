<%--
  Created by IntelliJ IDEA.
  User: Berk
  Date: 26.10.2015
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<%@ include file="/WEB-INF/pages/header.jsp" %>
<center>
    <form action="${contextPath}/login" method="post" class="form-inline" id="form">
        <table>
            <tr>
                <td><label for="username">Username</label></td>
                <td><input type="text" name="username" id="username" class="form-control"></td>
            </tr>
            <tr>
                <td><label for="password">Password</label></td>
                <td><input type="password" name="password" id="password" class="form-control"></td>
            </tr>
        </table>
        <button type="submit" class="btn btn-default">Log In</button>
        <c:if test="${doesUserExist == false}">
            <span style="color:red;">Your username or password is wrong!</span>
        </c:if>
    </form>
</center>
<%@ include file="/WEB-INF/pages/footer.jsp" %>