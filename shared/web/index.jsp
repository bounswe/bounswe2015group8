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

<div class="container-fluid">
    <div class="row">
        <div class="col-xs-2 col-xs-offset-3">
            <a href="${contextPath}/login" class="btn btn-primary" role="button">Log In</a>
        </div>
        <div class="col-xs-2 col-xs-offset-2">
            <a href="${contextPath}/signup" class="btn btn-success" role="button">Join Us!</a>
        </div>
    </div>
</div>



<%@ include file="/WEB-INF/pages/footer.jsp" %>