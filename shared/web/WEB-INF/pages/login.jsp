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
        <div class="roundbox" style="padding-top: 40px">
            <form action="${contextPath}/login" method="post" class="form-inline" id="form">
                <label for="username"><b>Username: </b></label>
                <input type="text" name="username" id="username" class="form-control"> <br>
                <label for="password"><b>Password: </b></label>
                <input type="password" name="password" id="password" class="form-control"> <br>
                <button type="submit" class="btn btn-default">Log In</button><br>
                <c:if test="${param['success'] == 'false'}">
                    <span style="color:red;">Your username or password is wrong!</span>
                </c:if>
                <c:if test="${param['resetPassword'] == 'true'}">
                    <span style="color:blue;">You have been sent the password reset link!</span>
                </c:if>
                <c:if test="${param['passwordChanged'] == 'true'}">
                    <span style="color:green;">Your password is successfully changed!</span>
                </c:if>
            </form>
            <a href="${contextPath}/forget_password">Forgot your password?</a>
        </div>
<%@ include file="/WEB-INF/pages/footer.jsp" %>

