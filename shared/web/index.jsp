<%--
  Created by IntelliJ IDEA.
  User: Berk
  Date: 26.10.2015
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ include file="/WEB-INF/pages/header.jsp" %>
<center>
    <form action="" method="post" class="form-inline" id="form">
        <a href="${contextPath}/login">Log In</a> <br>
        <a href="${contextPath}/signup">Join Us!</a>
    </form>
</center>
<%@ include file="/WEB-INF/pages/footer.jsp" %>